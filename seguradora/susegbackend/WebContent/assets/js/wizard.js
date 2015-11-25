searchVisible = 0;
transparent = true;

$(document).ready(function(){
	// xCodigoLoc - Código da localização, será salvo na cotação no final.

		var i=1;
		$("#add_row").click(function(){
			$('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='mail"+i+"' type='text' placeholder='Mail'  class='form-control input-md'></td><td><input  name='sexo"+i+"' type='radio' style='margin-top: 13px'> Masculino <input  name='sexo"+i+"' type='radio'> Feminino</td>");

			$('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
			i++; 

			$("#delete_row").click(function(){
				if(i>1){
					$("#addr"+(i-1)).html('');
					i--;
				}
			});

		});
	//http://bootsnipp.com/snippets/featured/dynamic-table-row-creation-and-deletion	

    /*  Activate the tooltips      */
    $('[rel="tooltip"]').tooltip();
      
    $('.wizard-card').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'nextSelector': '.btn-next',
        'searchSelector': '.btn-success',
        'newSelector': '.btn-info',
        'previousSelector': '.btn-previous',
         
         onInit : function(tab, navigation, index){
            
           //check number of tabs and fill the entire row
           var $total = navigation.find('li').length;
           $width = 100/$total;
           
           $display_width = $(document).width();
           
           if($display_width < 600 && $total > 3){
               $width = 50;
           }
           
           navigation.find('li').css('width',$width + '%');
           
        },
        onNext: function(tab, navigation, index){

            if (validateForm()){
        	
	            if (index == 3){ // Localização
	                saveLocalizacao();
	                //alert('Os dados de localização foram salvos.');
	            } else {
	            	if (index == 4){ 
		                saveSegurado();
		                //alert('Os dados do segurado foram salvos.');
	            	}
	            }

	            return true;    
        	} else {
        		return false;
        	}
            
        },
        onTabClick : function(tab, navigation, index){
            // Disable the posibility to click on tabs
            return false;
        }, 
        onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
			            
            var wizard = navigation.closest('.wizard-card');
            
            if($current == 1) {
                $(wizard).find('.btn-next').hide();
            } else { 					
				if($current == 2) {
					$(wizard).find('.btn-next').show();
				} else {
					// If it's the last tab then hide the last button and show the finish instead
					if($current >= $total) {
						$(wizard).find('.btn-next').hide();
						$(wizard).find('.btn-finish').show();
					} else {
						$(wizard).find('.btn-next').show();
						$(wizard).find('.btn-finish').hide();
					}
				}	
            }
        }
    });

    // Prepare the preview for profile picture
    $("#wizard-picture").change(function(){
        readURL(this);
    });

    $('.tipopessoa').change(function(){
        if ($("input[name='tipopessoa']:checked").val() == "pf"){
            $('.pessoajuridicaform').fadeOut('fast');   
            setTimeout(function() { 
                $('.pessoafisicaform').fadeIn('fast');
            }, 300);
            

        } else {
            if ($("input[name='tipopessoa']:checked").val() == "pj"){
                $('.pessoafisicaform').fadeOut('fast');
                setTimeout(function() { 
                    $('.pessoajuridicaform').fadeIn('fast');   
                }, 300);
                

            }
        }
    });
    
    $('[data-toggle="wizard-radio"]').click(function(){
        wizard = $(this).closest('.wizard-card');
        wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
        $(this).addClass('active');
        $(wizard).find('[type="radio"]').removeAttr('checked');
        $(this).find('[type="radio"]').attr('checked','true');

        $('.fabYear').fadeIn('slow');     
		$(".fabYear option").filter(function() {
			return this.text == "Selecione"; 
		}).attr('selected', true);
        $('.modelYear').fadeOut('slow');   		
        $('.aftermodelyear').fadeOut('slow');   
        $(wizard).find('.btn-next').show();
		$(wizard).find('.btn-next').attr("disabled", true);
    });
    
    $('[data-toggle="wizard-checkbox"]').click(function(){
        wizard = $(this).closest('.wizard-card');

        if ($(this).hasClass('active')){
            $(this).removeClass('active');
            $(this).find('[type="checkbox"]').removeAttr('checked');
        } else {
            $(this).addClass('active');
            $(this).find('[type="checkbox"]').attr('checked','true');
        }
    });

    $('.fabYear').change(function(){
        if ($('.fabYear').find(":selected").text() == "Selecione"){
            $('.modelYear').fadeOut('slow');  
			$('.aftermodelyear').fadeOut('slow');
			$("input[name='anomodelo']").attr("checked", false);
        } else {
            $('.modelYear').fadeIn('slow');   
			$('.aftermodelyear').fadeOut('fast');
			$("input[name='anomodelo']").attr("checked", false);
        }    
		
		$('.anomodelo01').text("  "+$('.fabYear').find(":selected").text());
		var xYear = parseInt($('.fabYear').find(":selected").text()) + 1;
		$('.anomodelo02').text("  "+xYear);
    })

    $('.modelYear').change(function(){
        $('.aftermodelyear').fadeIn('slow'); 
		
		$("select[name='carModel']").append($('<option>', {
			value: 1,
			text: 'My option'
		}));
    })
	
	$("select[name='carModel']").change(function(){
		$(wizard).find('.btn-next').attr("disabled", false);
	})
    
    $height = $(document).height();
    $('.set-full-height').css('height',$height);
    $('.image-container').css('height','100%');
    
});

function validateForm(){
    
    $(".wizard-card form").validate({
		rules: {
            carYear: "required",
            carModel: "required",
			mediaKM: "required",
			placa: "required",
			chassi: "required",
			renavam: "required",
			cor: "required",
            rua: "required",
            numero: "required",
            cidade: "required",
            estado: "required",
            nomesegurado: "required",
            datanasc: "required",
            cpf: "required",
            nomeempresa: "required",
            ie: "required",
            cnpj: "required",
            cep: "required",
            pais: "required",
            //jobb: "required",
			//lastname: "required",
			/*email: {
				required: true,
				email: true
			}*/
		

		},
		messages: {
            carYear: "Voc√™ deve informar o ano do ve√≠culo",
            carModel: "Voc√™ deve informar o ve√≠culo",
			mediaKM: "Voc√™ deve informar a m√©dia de KM",
			placa: "Voc√™ deve informar a Placa",
			chassi: "Voc√™ deve informar o Chassi",
			renavam: "Voc√™ deve informar o Renavam",
            cor: "Voc√™ deve informar a Cor",
            rua: "Voc√™ deve informar a Rua",
            numero: "Voc√™ deve informar o N√∫mero",
            cidade: "Voc√™ deve informar a Cidade",
            estado: "Voc√™ deve informar o Estado",
            nomesegurado: "Voc√™ deve informar o Nome do segurado",
            datanasc: "Voc√™ deve informar a Data de nascimento",
            cpf: "Voc√™ deve informar o CPF",
            nomeempresa: "Voc√™ deve informar o Nome da empresa",
            ie: "Voc√™ deve informar o IE",
            cnpj: "Voc√™ deve informar o CNPJ",
            cep: "Você deve informar o CEP",
            pais: "Você deve informar o País",
				
		}
		
	}); 
	
	if(!$(".wizard-card form").valid()){
    	//form is invalid
    	return false;
	}
	
	return true;
}


 //Function to show image before upload

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function saveSegurado(){
	if ($("input[name='tipopessoa']:checked").val() == "pf"){
		var xNomeSegurado = $("input[name='nomesegurado']").val();
		var xDataNasc     = $("input[name='datanasc']").val();
		var xCPF          = $("input[name='cpf']").val();
		var xTelefone     = $("input[name='telefone']").val();
		var xSexo         = $("input[name='sexo']:checked").val();
		
		xDataNasc.replace('/', '%2F');		
		params = "nome=" + xNomeSegurado + "&cpf=" + xCPF + "&dataNascimento=" + xDataNasc + "&telefone=" + xTelefone + "&sexo=" + xSexo;
		
	} else {
		if ($("input[name='tipopessoa']:checked").val() == "pj"){
			var xNomeEmpresa = $("input[name='nomeempresa']").val();
			var xIE          = $("input[name='ie']").val();
			var xCNPJ        = $("input[name='cnpj']").val();

			params = "";
		}
	}
	
	alert(params);

	//nome=Paulo&cpf=456.789.123-20&dataNascimento=08%2F09%2F1988&telefone=3332-3344&sexo=M
	xReturn = httpGet("http://localhost:8080/susegbackend/GravaSegurado?" + params);
	
	// { codigo: 8, nome: 0, cpf: 0, sexo: M, telefone: 0, dataNascimento: 01/01/1980  }
	alert(xReturn);
	try{	
		xSegSaved = eval ("(" + xReturn + ")");
		alert('aaaa');
	} catch (e) {
	    if (e instanceof SyntaxError) {
	        alert(e.message);
	    } else {
	        throw( e );
	    }
	}
    //xCodigoSegurado = xSegSaved.codigo;
    alert('abc');
	alert(xSegSaved);
    
	return xReturn;
}

function saveLocalizacao(){
	var xRua    = $("input[name='rua']").val();
	var xCidade = $("input[name='cidade']").val();
	var xNumero = $("input[name='numero']").val();
	var xEstado = $("input[name='estado']").val();
	var xCep    = $("input[name='cep']").val();
	var xPais   = $("input[name='pais']").val();
	
    params = "numero=" + xNumero + "&rua=" + xRua + "&cep=" + xCep + "&cidade=" + xCidade + "&estado=" + xEstado + "&pais=" + xPais;
    xReturn = httpGet("http://localhost:8080/susegbackend/GravaLocalizacao?" + params);
    
    // { codigo: 6, rua: Teste, numero: 999, cep: 900312, cidade: Blu, estado: SC, pais: Brasil  }
    xLocSaved = eval ("(" + xReturn + ")"); 
    xCodigoLoc = xLocSaved.codigo;       
    
    return xReturn; 
}

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
    







