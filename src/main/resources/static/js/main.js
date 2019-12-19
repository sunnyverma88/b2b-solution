$(document).ready(function(){

var quantity=0;
   $('.quantity-right-plus').click(function(e){

        // Stop acting like a button
        e.preventDefault();
        // Get the field name
         quantity = parseInt($('#quantity').val());
            $('#quantity').val(quantity + 1);
    });

     $('.quantity-left-minus').click(function(e){
        // Stop acting like a button
        e.preventDefault();
        // Get the field name
         quantity = parseInt($('#quantity').val());

            if(quantity>0){
            $('#quantity').val(quantity - 1);
            }
    });

});

$(document).ready(function(){
$("input").prop('required',true);
 $('#same-address').prop('required',false);
    $('#same-address').change(function(){
        if(this.checked){
            $('#shippingAddress').fadeOut('slow');
             $("input").prop('required',false);
             $('#same-address').prop('required',true);}

        else
        {  $('#shippingAddress').fadeIn('slow');
            $("input").prop('required',true);
             $('#same-address').prop('required',false);}
    });
});

$(document).ready(function(){
        $(".show-toast").click(function(){
            $("#myToast").toast('show');
        });
    });
$(document).ready(function(){
 $('#printPO').click(function(){
            Popup();
            function Popup()
            {    var topNav = document.getElementById("top_nav");
                 var poTitle=document.getElementById("po_title");
                         //Set the print button visibility to 'hidden'
                         topNav.style.visibility = 'collapse';
                         poTitle.style.visibility='collapse';
                  window.print();
                  topNav.style.visibility = 'visible';
                  poTitle.style.visibility='visible';
                  return true;
            }
        });
});

$('#approveRejectModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var taskId = button.data('whatever') // Extract info from data-* attributes
  var action = button.data('action')
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)

  modal.find('.modal-body #taskId').val(taskId)
  modal.find('.modal-body #action').val(action)
});



