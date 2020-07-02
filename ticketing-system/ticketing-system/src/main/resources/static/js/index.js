/**
 * 
 */

$( document ).ready(function() {
    console.log( "ready!" );
    $(".table #edit").on("click",function(event){
    	event.preventDefault();
    	console.log("edit button clicked");
    	
    	var href = $(this).attr('href');
    	$.get(href,function(ticket, status){
    		$('.modal-body #id').val(ticket.id);
    		$('.modal-body #subject').val(ticket.subject);
    		$('.modal-body #priority').val(ticket.priority);
    		$('.modal-body #description').val(ticket.description);
    		$('.modal-body #raisedOn').val(ticket.raisedOn);
    		
    	})
    	$("#ticketModalUpdate").modal();
    })
});

function editTicket(){
	
}



function deleteTicket(id){
	console.log("id for deletion"+id);
	/*$("#delete").on("click",function(id){
		$.ajax({
			   type:'DELETE',
			   url :"/ticket/"+id,
			   success: function(data) {
			        console.log('success',data);
			   },
			   error:function(exception){alert('Exeption:'+exception);}
	});*/
}

	