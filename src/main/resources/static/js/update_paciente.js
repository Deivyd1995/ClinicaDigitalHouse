$(document).ready(function(){
    $("#update_paciente_form").submit(function(evt) {
        evt.preventDefault();
        try {
            let pacienteId = $("#paciente_id").val();
            
        let formData = {
            id: $("#paciente_id").val(),
            name : $("#nombre").val(),
            lastName :  $("#apellido").val(),
            dni: $("#dni").val(),
            admissionDate: $("#fechaIngreso").val(),
            address:{
                street: $("#calle").val(),
            number: $("#numero").val(),
            location: $("#localidad").val(),
            province: $("#provincia").val(), 
             
            }
        }
            
            $.ajax({
                url: '/patient/update',
                type: 'PUT',
                contentType : "application/json",
                data: JSON.stringify(formData),
                dataType : 'json',
                async: false,
                cache: false,
                success: function (response) {
                    let paciente = response;
        
                    let successAlert = '<div class="alert alert-success alert-dismissible">' + 
                                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                            '<strong> paciente actualizado </strong></div>'

                 
                    $("#tr_" + pacienteId + " td.td_first_name").text(paciente.name.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_last_name").text(paciente.lastName.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_dni").text(paciente.dni);
                    $("#tr_" + pacienteId + " td.td_fechaIngreso").dataType(paciente.admissionDate);
                    $("#tr_" + pacienteId + " td.td_street").text(paciente.address.street.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_number").text(paciente.address.number);
                    $("#tr_" + pacienteId + " td.td_location").text(paciente.address.location.toUpperCase());
                    $("#tr_" + pacienteId + " td.td_province").text(paciente.address.province.toUpperCase());


                    $("#response").empty();
                    $("#response").append(successAlert);
                    $("#response").css({"display": "block"});
                    $("#update_paciente_form").css({"display": "block"});
                },

                error: function (response) {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' + 
                                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                        '<strong> Error </strong></div>';

                    $("#response").empty();                                    
                    $("#response").append(errorAlert);
                    $("#response").css({"display": "block"});
                }
            });
        } catch(error){
            console.log(error);
            alert(error);
        }
    });

    $(document).on("click", "table button.btn_id", function(){
        let id_of_button = (event.srcElement.id);
        let pacienteId = id_of_button.split("_")[2];
  
        $.ajax({
            url: '/patient/id/' + pacienteId,
            type: 'GET',
            success: function(response) {
                let paciente = response;                
                $("#paciente_id").val(paciente.id);
                $("#nombre").val(paciente.name);
                $("#apellido").val(paciente.lastName);
                $("#dni").val(paciente.dni);
                $("#fechaIngreso").val(paciente.admissionDate);
                $("#calle").val(paciente.address.street);
                $("#numero").val(paciente.address.number);
                $("#localidad").val(paciente.address.location);
                $("#provincia").val(paciente.address.province);
                $("#div_paciente_updating").css({"display": "block"});
            },
            error: function(error){
                console.log(error);
                alert("Error -> " + error);
            }
        });        
    });
});