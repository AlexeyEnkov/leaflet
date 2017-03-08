if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'leaflet'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'leaflet'.");
}
var leaflet = function (_, Kotlin) {
  'use strict';
  function main$ObjectLiteral() {
    this.attribution = '&copy; <a href="http://osm.org/copyright">OpenStreetMap<\/a> contributors';
  }
  main$ObjectLiteral.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    interfaces: []
  };
  function main$lambda(closure$myMap) {
    return function (it) {
      console.log(closure$myMap.getBounds());
    };
  }
  function main(args) {
    var tmp$;
    var myMap = L.map('mapid').setView([51.505, -0.09], 13);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', new main$ObjectLiteral()).addTo(myMap);
    var button = Kotlin.isType(tmp$ = document.getElementById('button'), HTMLElement) ? tmp$ : Kotlin.throwCCE();
    button.onclick = main$lambda(myMap);
  }
  var package$ru = _.ru || (_.ru = {});
  var package$spb = package$ru.spb || (package$ru.spb = {});
  var package$enkov = package$spb.enkov || (package$spb.enkov = {});
  var package$leaflet = package$enkov.leaflet || (package$enkov.leaflet = {});
  package$leaflet.main_kand9s$ = main;
  Kotlin.defineModule('leaflet', _);
  main([]);
  return _;
}(typeof leaflet === 'undefined' ? {} : leaflet, kotlin);
