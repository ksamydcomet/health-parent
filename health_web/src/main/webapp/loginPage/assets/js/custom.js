$(document).ready(function () {
    'use strict';

    //One Page Nav
    $('#one-page-nav').onePageNav({
        currentClass: 'active',
    });

    // Search toggle
    $('#search-toggle').on('click', function (st) {
        st.preventDefault();
        $('#header-search').slideToggle();
    });

    //Magnific Popup
    $('.image-large').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true
        }
    });
    $('.video-play').magnificPopup({
        type: 'iframe'
    });
    $.extend(true, $.magnificPopup.defaults, {
        iframe: {
            patterns: {
                youtube: {
                    index: 'youtube.com/',
                    id: 'v=',
                    src: 'http://www.youtube.com/embed/%id%?autoplay=1'
                }
            }
        }
    });

    //Screenshot carousel
    $('#screenshot-carousel').owlCarousel({
        items: 5,
        itemsDesktop: [1199, 5],
        itemsDesktopSmall: [991, 3],
        itemsTablet: [767, 1],
        itemsMobile: [479, 1],
        slideSpeed: 200,
        autoPlay: 3000,
        stopOnHover: true,
        navigation: false,
        pagination: true,
    });

    //Testimonial Carousel
    $('#testimonial-carousel').owlCarousel({
        singleItem: true,
        slideSpeed: 200,
        autoPlay: 3000,
        stopOnHover: true,
        navigation: true,
        navigationText: ['<i class=\"fa fa-angle-left\"></i>', '<i class=\"fa fa-angle-right\"></i>'],
        pagination: false,
    });

    // Mailchimp Subscription
    $(".subscription").ajaxChimp({
        callback: mailchimpResponse,
        url: "" // Replace your mailchimp post url inside double quote "".
    });
    function mailchimpResponse(resp) {
        if (resp.result === 'success') {
            $('.newsletter-success').html(resp.msg).fadeIn().delay(3000).fadeOut();
        } else if (resp.result === 'error') {
            $('.newsletter-error').html(resp.msg).fadeIn().delay(3000).fadeOut();
        }
    }

    // Contact form

    // Function for email address validation
    function isValidEmail(emailAddress) {
        var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
        return pattern.test(emailAddress);
    }
    $("#contactForm").on('submit', function (e) {
        e.preventDefault();
        var contactDetails = {}
        contactDetails.ucmName = $('#name').val();
        contactDetails.ucmMobileNumber = $('#mobileNo').val();
        contactDetails.ucmContactEmail = $('#email').val();
        contactDetails.ucmMessages = $('#message').val();
        contactDetails.ucmStatus = 0;
        contactDetails.ucmType = 1;
        contactDetails.ucmTypeRef = $('#type').val() == 1 ? "Optical Showroom" : $('#type').val() == 2 ? "Lens laboratory" : $('#type').val() == 2 ? "Frame factory" : "Apptics";
        if (!!$('#name').val() && !!$('#mobileNo').val() && !!$('#email').val() && !!$('#message').val()) {
            dcomethealth.EmailResource.saveContactDetails(contactDetails).done(function (data, textStatus, jqXHR) {
                alert("Congratulation, your message sent");
//                $('#contactForm .input-success').fadeIn(100);
//                $('#contactForm .input-error').fadeOut(500);
                window.location.reload();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert("Sorry, Something went wrong");
                window.location.reload();
//                $('#contactForm .input-success').delay(500).fadeIn(1000);
//                $('#contactForm .input-error').fadeOut(500);
            });
        } else {
            alert('Dont Leave Any Field');
            return false;
        }

//        if (isValidEmail(data['email']) && (data['message'].length > 1) && (data['name'].length > 1) && (data['mobileno'].length > 1)) {
//            $.ajax({
//                type: "POST",
//                url: "sendmail.php",
//                data: data,
//                success: function () {
//                    $('#contactForm .input-success').delay(500).fadeIn(1000);
//                    $('#contactForm .input-error').fadeOut(500);
//                }
//            });
//        } else {
//            $('#contact-form .input-error').delay(500).fadeIn(1000);
//            $('#contact-form .input-success').fadeOut(500);
//        }
        return false;
    });
});
