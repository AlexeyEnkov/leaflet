@file:Suppress("PackageDirectoryMismatch")

package ru.spb.enkov.leaflet

import org.w3c.dom.*
import org.w3c.fetch.Response
import kotlin.browser.document
import kotlin.browser.window

data class Content(val input : HTMLInputElement, val button : HTMLDivElement)

fun main(args: Array<String>) {
    window.onload = { App() }
}

class App {

    private val map : Leaflet

    init {
        val content = generateContent()

        map = Leaflet("mapId")

        content.button.onclick = {
            query(content.input.value, map)
        }
    }

    fun generateContent() : Content {
        val mapDiv = document.createElement("div") as HTMLElement
        mapDiv.id = "mapId"
        mapDiv.style.height = "500px"

        val input = document.createElement("input") as HTMLInputElement
        input.id = "inputId"
        input.value = "72"

        val buttonDiv = document.createElement("div") as HTMLDivElement
        buttonDiv.id = "buttonId"
        buttonDiv.innerText = "Search"
        buttonDiv.style.color = "green"
        buttonDiv.style.display = "inline-block"

        val body = document.body!!
        body.appendChild(input)
        body.appendChild(buttonDiv)
        body.appendChild(mapDiv)

        return Content(input, buttonDiv)
    }

    fun query(number: String, map: Leaflet) {
        showText("query begin")
        clear()
        val bounds = map.getBounds()
        showText("bounds")
        val query = "[out:json];(" +
                    "node[\"addr:housenumber\"=\"$number\"]" +
                    "$bounds;" +
                    "way[\"addr:housenumber\"=\"$number\"]" +
                    "$bounds;" +
                    "rel[\"addr:housenumber\"=\"$number\"]" +
                    "$bounds;" +
                    ");out center;"
        showText("beforeFetch")
        window.fetch("https://overpass-api.de/api/interpreter?data=$query")
                .then({
                    showText("before json parse")
                    it.json()
                })
                .then({
                    showText("after json parse")
                    val elements : Array<OSMElement> = it.asDynamic().elements
                    showText("after elements to array")
                    elements.forEach { element ->
                        map.addMarker(element.lat ?: element.center!!.lat, element.lon ?: element.center!!.lon)
                    }
                    showText("after marker")
                })
                .catch { showText(it.toString()) }
        showText("afterFetch")
    }

    fun showText(s : String) {
        val text = document.createElement("div") as HTMLDivElement
        text.className = "text"
        text.innerText = s
        document.body!!.appendChild(text)
    }

    fun clear() {
        val c = document.getElementsByClassName("text")
        while (c.length > 0) c[0]!!.remove()
        map.clearAllMarkers()
    }

}

class OSMElement(val type: String, val id: Long, val center: Coord?, val lat: Double?, val lon: Double?)

class Coord(val lat: Double, val lon: Double)