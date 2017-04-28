// get exported json from cytoscape desktop via ajax
var graphP = $.ajax({
	url : 'resources/json/lyon_metro.json', // tokyo-railways.json
	dataType : 'json'
});

// also get style via ajax
var styleP = $.ajax({
	url : 'resources/css/styleNode.txt',
	dataType : 'text'
});

var depart = "temp_depart";
var arrivee = "temp_arrivee";

var departTemp;

$(document).ready(function() {
	Promise.all([ graphP, styleP ]).then(loadResult);
	// Promise.all([ graphP, styleP ]).then(initCy);

});

function loadResult(then) {
	$.ajax({
		datatype : "json",
		type : 'GET',
		url : 'result',
		success : function(respond) {
			var res = respond;
			var j = 0;
			$.each(res, function(i, val) {

				var it = 0;
				for ( var k in val) {
					switch (k) {
					case "dureeVisite":
						addDureeVisite(val[k]);
						break;
					case "nomSite":
						createDivNomSite(val[k]);
						break;
					default:
						for (var j = 0; j < 6; j++) {
							switch (j) {
							case 0:
								addDivMetro(val[k][j].metro);
								break;
							case 1:
								addDivHeureDepart(val[k][j].heureDepart);
								break;
							case 2:
								addDivDepart(val[k][j].depart);
								if (it == 0)
									depart = val[k][j].depart;
								break;
							case 3:
								addDivDirection(val[k][j].direction);
								break;

							case 4:
								addDivHeureArrivee(val[k][j].heureArrivee);
								break;
							case 5:
								addDivArrivee(val[k][j].arrivee);
								arrivee = val[k][j].arrivee;
								break;

							default:
								break;
							}
						}
						it++;
						break;
					}

				}
				addDivItineraire();
				initCy(then);

			});

		}
	});
}

function initCy(then) {

	var expJson = then[0];
	var styleJson = then[1];
	var elements = expJson.elements;

	var cy = cytoscape({
		container : $('.itineraire:last'),
		layout : {
			name : 'preset'
		},
		style : styleJson,
		elements : elements,
		motionBlur : true,
		selectionType : 'single',
		boxSelectionEnabled : false,
		autolock : true,

	});
	var nodes = cy.nodes();
	var edges = cy.edges();
	for (var i = 0; i < nodes.length; i++) {
		if (nodes[i].data('station_name') == depart)
			var nodeDepart = '#' + i;
		if (nodes[i].data('station_name') == arrivee) {
			var nodeArrivee = '#' + i;

		}

	}

	var aStar = cy.elements().aStar({
		root : nodeDepart,
		goal : nodeArrivee,

	});
	cy.elements().not(aStar.path).addClass('not-path');
	aStar.path.addClass('path');

	cy.endBatch();

}

function createDivNomSite(val){
  $('#result').append("<div class=\"site\"></div>");
  $('.site:last').append("<div class=\"row\"></div>");
  $('.row:last:last').text(val);
}

function addDureeVisite(val){
  var heure = getHour(val);
  if (heure != 0){
    $('.site:last').append("<div class=\"row\"></div>");
    $('.row:last').text('Durée de visite conseillée : '+ heure);
  }
}

function addDivMetro(val){
  $('.site:last').append("<div class=\"panel panel-default\"></div>")
  $('.panel.panel-default:last').append("<div class=\"row\"></div>");
  $('.row:last').append("<div class=\"col-lg-4\"></div>");
  $('.col-lg-4:last').text(val);
}

function addDivHeureDepart(val){
  $('.row:last').append("<div class=\"col-lg-4\"></div>");
  var heure = transformToHour(val);
  $('.col-lg-4:last').html("<strong>"+heure+"</strong>");
}

function addDivDepart(val){
  $('.row:last').append("<div class=\"col-lg-4\"></div>");
  $('.col-lg-4:last').html(val);
}

function addDivDirection(val){
  $('.panel.panel-default:last').append("<div class=\"row\"></div>");
  $('.row:last').append("<div class=\"col-lg-offset-8 col-lg-4\"></div>");
  $('.col-lg-offset-8.col-lg-4:last').append("Direction "+val);
}

function addDivHeureArrivee(val){
  $('.panel.panel-default:last').append("<div class=\"row\"></div>");
  $('.row:last').append("<div class=\"col-lg-offset-4 col-lg-4\"></div>");
  var heure = transformToHour(val);
  $('.col-lg-offset-4.col-lg-4:last').text(heure);

}

function addDivArrivee(val){
  $('.row:last').append("<div class=\"col-lg-4\"></div>");
  $('.col-lg-4:last').text(val);
}



function addDivItineraire(){
  $('.site:last').append("<div class=\"row\" ><div class= \"itineraire\"></div></div>");

}

function transformToHour(val){
  if (val ==0){
    return '00:00';
  }
  var heure = ~~(val/100);
  var min = val%100;
  if (min <10){
    min = '0'+min;
  }
  return heure+':'+min;

}

function getHour(val){
  var heure = ~~(val / 100);
  var min = val % 100;
  var txtMin = ' minutes';
  if (val==0)
    return 0;
  if (min < 10){
    if (min <=1){
      txtMin = ' minute';
    }
    min = '0'+min;

  }
  if (heure <1){
     return min+txtMin;
  }
  
  return heure+' heure et '+min+txtMin;
}