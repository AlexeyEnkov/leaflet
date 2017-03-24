@file:Suppress("PackageDirectoryMismatch")

package ru.spb.enkov.leaflet

external val L: dynamic

class Leaflet(mapId: String) {

    private val map = L.map(mapId).setView(arrayOf(55.85, 38.46), 13)
    private val markers: MutableSet<dynamic>

    init {
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", object {
            val attribution = "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"
        }).addTo(map)
        markers = HashSet()
    }

    fun getBounds() : String {
        val bounds = map.getBounds()
        return "(${bounds._southWest.lat},${bounds._southWest.lng},${bounds._northEast.lat},${bounds._northEast.lng})"
    }

    fun addMarker(lat: Double, lon: Double) {
        val marker = L.marker(arrayOf(lat, lon))
        marker.addTo(map)
        markers.add(marker)
    }

    fun clearAllMarkers() {
        markers.forEach { it.remove() }
    }
}