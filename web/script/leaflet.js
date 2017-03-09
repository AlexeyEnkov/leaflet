if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'leaflet'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'leaflet'.");
}
var leaflet = function (_, Kotlin) {
  'use strict';
  function main$lambda(it) {
    init();
  }
  function main(args) {
    window.onload = main$lambda;
  }
  function init$ObjectLiteral() {
    this.attribution = '&copy; <a href="http://osm.org/copyright">OpenStreetMap<\/a> contributors';
  }
  init$ObjectLiteral.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    interfaces: []
  };
  function init$lambda(closure$map) {
    return function (it) {
      console.log(closure$map.getBounds());
    };
  }
  function init() {
    var tmp$;
    generateContent();
    var map = L.map('mapId').setView([51.505, -0.09], 13);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', new init$ObjectLiteral()).addTo(map);
    var buttonDiv = Kotlin.isType(tmp$ = document.getElementById('buttonId'), HTMLElement) ? tmp$ : Kotlin.throwCCE();
    buttonDiv.onclick = init$lambda(map);
  }
  function generateContent() {
    var tmp$, tmp$_0, tmp$_1;
    var mapDiv = Kotlin.isType(tmp$ = document.createElement('div'), HTMLElement) ? tmp$ : Kotlin.throwCCE();
    mapDiv.id = 'mapId';
    mapDiv.style.height = '600px';
    var buttonDiv = Kotlin.isType(tmp$_0 = document.createElement('div'), HTMLElement) ? tmp$_0 : Kotlin.throwCCE();
    buttonDiv.id = 'buttonId';
    buttonDiv.innerText = 'Button';
    buttonDiv.style.color = 'green';
    var body = (tmp$_1 = document.body) != null ? tmp$_1 : Kotlin.throwNPE();
    body.appendChild(mapDiv);
    body.appendChild(buttonDiv);
  }
  var package$ru = _.ru || (_.ru = {});
  var package$spb = package$ru.spb || (package$ru.spb = {});
  var package$enkov = package$spb.enkov || (package$spb.enkov = {});
  var package$leaflet = package$enkov.leaflet || (package$enkov.leaflet = {});
  package$leaflet.main_kand9s$ = main;
  package$leaflet.init = init;
  package$leaflet.generateContent = generateContent;
  Kotlin.defineModule('leaflet', _);
  main([]);
  return _;
}(typeof leaflet === 'undefined' ? {} : leaflet, kotlin);
