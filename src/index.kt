package ru.spb.enkov.leaflet

import org.w3c.dom.HTMLElement
import kotlin.browser.document

external val L: dynamic

fun main(args: Array<String>) {
    val myMap = L.map("mapid").setView(arrayOf(51.505, -0.09), 13)


    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", object {
        val attribution = "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"
    }).addTo(myMap)

    val button = document.getElementById("button") as HTMLElement
    button.onclick = {
        console.log(myMap.getBounds())
    }
}