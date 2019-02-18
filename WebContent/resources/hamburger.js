$(document)
		.ready(
				function() {

var hamburgerOpen = false;

document
.getElementById("hamburger")
.addEventListener(
		"click",
		function() {
			hamburgerOpen = !hamburgerOpen;
			if (hamburgerOpen) {
				$("#hamburger").addClass(
						"is-active");
				document.getElementById("cssmenu").style.marginLeft = "0px";
			} else {
				$("#hamburger").removeClass(
						"is-active");
				document.getElementById("cssmenu").style.marginLeft = "-250px";
			}
		});

	document.getElementById("logoutBtn")
	.addEventListener(
			"click",
			function() {
				document.getElementById("logoutSubmit")
				.click();
			});
	
	$('#cssmenu li.has-sub>a')
	.on(
			'click',
			function() {
				$(this).removeAttr('href');
				var element = $(this).parent('li');
				if (element.hasClass('open')) {
					element.removeClass('open');
					element.find('li').removeClass(
							'open');
					element.find('ul').slideUp();
				} else {
					element.addClass('open');
					element.children('ul').slideDown();
					element.siblings('li').children(
							'ul').slideUp();
					element.siblings('li').removeClass(
							'open');
					element.siblings('li').find('li')
							.removeClass('open');
					element.siblings('li').find('ul')
							.slideUp();
				}
			});

	
});

