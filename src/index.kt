@file:Suppress("PackageDirectoryMismatch")

package ru.spb.enkov.leaflet

import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.browser.window

external val L: dynamic

fun main(args: Array<String>) {
    window.onload = { init() }
}

fun init() {
    generateContent()

    val map = L.map("mapId").setView(arrayOf(51.505, -0.09), 13)

    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", object {
        val attribution = "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"
    }).addTo(map)

    val buttonDiv = document.getElementById("buttonId") as HTMLElement
    buttonDiv.onclick = {
        console.log(map.getBounds())
    }
}

fun generateContent() {
    val mapDiv = document.createElement("div") as HTMLElement
    mapDiv.id = "mapId"
    mapDiv.style.height = "600px"

    val buttonDiv = document.createElement("div") as HTMLElement
    buttonDiv.id = "buttonId"
    buttonDiv.innerText = "Button"
    buttonDiv.style.color = "green"

    val body = document.body!!
    body.appendChild(mapDiv)
    body.appendChild(buttonDiv)
}