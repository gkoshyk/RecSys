$(function() {
	$.getJSON("http://localhost:8080/RecSys/service/locations?pageNo=1?",requestOne);
});

function requestOne(data) 
	{
	alert("dummy");
	jsonObjLocations = []
		for (var i = 0; i < data.data.length; i++) 
		{
		itemLocations = {}
		itemLocations["text"] = data.data[i].name;
		itemLocations["weight"] = data.data[i].count;
		jsonObjLocations.push(itemLocations);
		}
		var json2 = {
				handlers : {
					click : function() 
							{
							$.getJSON("http://localhost:8080/RecSys/service/artists/locn/Georgia?pageNo=1",requestTwo);
							}
						   }
					}

		for (var i = 0; i < data.data.length; i++) 
		{
		$.extend(true, jsonObjLocations[i], json2);
		}
	$("#example").jQCloud(jsonObjLocations);
	}

function requestTwo(data) {
	alert("dummyTwo");
	var count =1;
	jsonObjArtists = []
	for (var i = 0; i < data.data.length; i++) 
	{
	itemArtists = {}
	itemArtists["text"] = data.data[i].name;
	itemArtists["weight"] = coutn;
	jsonObjLocations.push(itemArtists);
	}
	

	for (var i = 0; i < data.data.length; i++) {
		$.extend(true, jsonObj[i], json2);
	}

	$("#example2").jQCloud(jsonObj);

}
