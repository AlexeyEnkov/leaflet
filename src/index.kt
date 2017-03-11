@file:Suppress("PackageDirectoryMismatch")

package ru.spb.enkov.leaflet

import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.browser.window

external val L: dynamic

data class Content(val input : HTMLInputElement, val button : HTMLDivElement)

fun main(args: Array<String>) {
    window.onload = { init() }
}

fun init() {
    val content = generateContent()

    val map = L.map("mapId").setView(arrayOf(55.85, 38.46), 13)

    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", object {
        val attribution = "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"
    }).addTo(map)

    content.button.onclick = {
        query(content.input.value, map)
    }
}

fun generateContent() : Content {
    val mapDiv = document.createElement("div") as HTMLElement
    mapDiv.id = "mapId"
    mapDiv.style.height = "600px"

    val input = document.createElement("input") as HTMLInputElement
    input.id = "inputId"
    input.value = "72"

    val buttonDiv = document.createElement("div") as HTMLDivElement
    buttonDiv.id = "buttonId"
    buttonDiv.innerText = "Search"
    buttonDiv.style.color = "green"
    buttonDiv.style.display = "inline-block"

    val body = document.body!!
    body.appendChild(mapDiv)
    body.appendChild(input)
    body.appendChild(buttonDiv)

    return Content(input, buttonDiv)
}

fun query(number: String, map: dynamic) {
    val bounds = getBounds(map)
    val query = "[out:json];(" +
                "node[\"addr:housenumber\"=\"$number\"]" +
                "$bounds;" +
                "way[\"addr:housenumber\"=\"$number\"]" +
                "$bounds;" +
                "rel[\"addr:housenumber\"=\"$number\"]" +
                "$bounds;" +
                ");out center;"
    window.fetch("http://overpass-api.de/api/interpreter?data=$query")
            .then({
                it.json()
            })
            .then({
                val elements : Array<OSMElement> = it.asDynamic().elements
                elements.forEach { element ->
                    L.marker(arrayOf(element.center.lat, element.center.lon)).addTo(map)
                }
            })
}

fun getBounds(map: dynamic) : String {
    val bounds = map.getBounds()
    return "(${bounds._southWest.lat},${bounds._southWest.lng},${bounds._northEast.lat},${bounds._northEast.lng})"
}

class OSMElement(val type: String, val id: Long, val center: Coord)

class Coord(val lat: Double, val lon: Double)