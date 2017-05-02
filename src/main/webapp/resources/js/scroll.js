$(document).ready(function() {
	$('#dateJour').datetimepicker({
		format:"DD/MM/YY", 
	});
	$('#heureArrivee').datetimepicker({
		format:'HH:mm', 
	});

	$('#heureDepart').datetimepicker({
		format:'HH:mm', 
	});

	$("#heureArrivee").on("dp.change", function (e) {
		$('#heureDepart').data("DateTimePicker").minDate(e.date);
	});
	$("#heureDepart").on("dp.change", function (e) {
		$('#heureArrivee').data("DateTimePicker").maxDate(e.date);
	});



});

$(function() {
    $(document).on('click', 'a.page-scroll', function(event) {

        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });
});

$(window).scroll(function() {
    if ($(".navbar").offset().top > 50) {
        $(".navbar-fixed-top").addClass("top-nav-collapse");
    } else {
        $(".navbar-fixed-top").removeClass("top-nav-collapse");
    }
});