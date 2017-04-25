
  // get exported json from cytoscape desktop via ajax
var graphP = $.ajax({
  url: 'resources/json/lyon_metro.json', // tokyo-railways.json
  dataType: 'json'
});

// also get style via ajax
var styleP = $.ajax({
  url: 'resources/css/styleNode.txt',
  dataType: 'text'
});

var depart = "temp_depart";
var arrivee = "temp_arrivee";


$(document).ready(function() {
  Promise.all([ graphP, styleP ]).then(loadResult);
  //Promise.all([ graphP, styleP ]).then(initCy);

});




function loadResult(then){
		$.ajax({
			datatype:"json",
			type:'GET',
			url:'result',
			success:function(respond){
				var res = respond;
        var j =0;
				$.each(res,function(i, val){
          
          var it =0;
					for (var k in val){
						switch (k){
							case "dureeVisite": addDureeVisite(val[k]); break;
							case "nomSite": createDivNomSite(val[k]); break;
							default : 
								for (var j = 0; j<6;j++){
									switch(j){
										case 0: addDivMetro(val[k][j].metro); break;
										case 1: addDivDirection(val[k][j].direction); break;
										case 2: addDivDepart(val[k][j].depart);
                            if (it == 0)
                              depart = val[k][j].depart;
                            break;
										case 3: addDivHeureDepart(val[k][j].heureDepart); break;
										case 4: addDivArrivee(val[k][j].arrivee);
                            arrivee = val[k][j].arrivee;
                            break;
										case 5: addDivHeureArrivee(val[k][j].heureArrivee); break;
										default: break;
									}
								}
                it++;
								break;
						}
           
					}
          addDivItineraire();
          initCy(then);
          getItineraire();
          
				});
        
			}
		});
}


function initCy(then){
  
    var expJson = then[0];
    var styleJson = then[1];
    var elements = expJson.elements;

   
    var cy  = cytoscape({
      container: $('.itineraire:last'),
      layout: { name: 'preset' },
      style: styleJson,
      elements: elements,
      motionBlur: true,
      selectionType: 'single',
      boxSelectionEnabled: false,
      autolock:true,

    
    });
    cy.startBatch();
    var edges = elements.edges;
    console.log(elements);
    var color = '#ce0018';
    for (var i =0; i < edges.length; i++){
      if (i == 13)
        color = '#729fcf';
      if (i == 22)
        color = '#efad29';
      if (i == 25)
        color = '#39a552';
      cy.add({
          group: 'edges',
          data: {
            source: edges[i].data.source,
            target: edges[i].data.target,
            is_walking: true
          },
          style:  {
            'line-color':color
          }
          
          
        });
       
    }
    

    cy.endBatch(); //.autolock( true );
    
}


function getItineraire(){
  /*
  
  var aStar = cy.elements().aStar({
  root: depart,
  goal: arrivee,
  weight: function( e ){
    if( e.data('is_walking') ){
      return 0.25; // assume very little time to walk inside stn
    }
    
    return e.data('is_bullet') ? 1 : 3; // assume bullet is ~3x faster
  }
});
*/
}


function addDivItineraire(){
  $('.site:last').append("<div class=\"itineraire\" ></div>");
  getItineraire();

}

function createDivNomSite(val){
	$('#result').append("<div class=\"site\"></div>");
	$('.site:last').append("<div class=\"nomSite\"></div>");
	$('.nomSite:last').text(val);
}

function addDureeVisite(val){
	$('.site:last').append("<div class=\"dureeVisite\"></div>");
	$('.dureeVisite:last').text(val);
}

function addDivMetro(val){
	$('.site:last').append("<div class=\"metro\"></div>");
	$('.metro:last').text(val);
}

function addDivDirection(val){
	$('.site:last').append("<div class=\"direction\"></div>");
	$('.direction:last').text(val);
}

function addDivDepart(val){
	$('.site:last').append("<div class=\"depart\"></div>");
	$('.depart:last').text(val);
}

function addDivHeureDepart(val){
	$('.site:last').append("<div class=\"heureDepart\"></div>");
	$('.heureDepart:last').text(val);
}

function addDivArrivee(val){
	$('.site:last').append("<div class=\"arrivee\"></div>");
	$('.arrivee:last').text(val);
}

function addDivHeureArrivee(val){
	$('.site:last').append("<div class=\"heureArrivee\"></div>");
	$('.heureArrivee:last').text(val);
}
