$(document).ready(function(){
    (function(){
        $.ajax({
            type : "GET",
            url : "/patient/viewAll",
            success: function(response){
              $.each(response, (i, paciente) => {  

                console.log(response)


                let get_More_Info_Btn = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                            ' type="button" class="btn btn-info btn_id">' + 
                                            paciente.id +
                                            '</button>';
                
                let tr_id = 'tr_' + paciente.id;
                let pacienteRow = '<tr id=\"' + tr_id + "\"" + '>' +
                          '<td>' + get_More_Info_Btn + '</td>' +
                          '<td class=\"td_first_name\">' + paciente.name.toUpperCase() + '</td>' +
                          '<td class=\"td_last_name\">' + paciente.lastName + '</td>' +
                          '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                          '<td class=\"td_fechaIngreso\">' + paciente.admissionDate + '</td>' +
                          '<td class=\"td_id_Domiclio\">' + paciente.address.id + '</td>' +
                          '<td class=\"td_calle\">' + paciente.address.street + '</td>' +
                          '<td class=\"td_numero\">' + paciente.address.number + '</td>' +
                          '<td class=\"td_localidad\">' + paciente.address.location + '</td>' +
                          '<td class=\"td_provincia\">' + paciente.address.province  + '</td>' +
                          '</tr>';                
                $('#pacienteTable tbody').append(pacienteRow);
              });
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });
    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});