searchVisible = 0;
transparent = true;

$(document).ready(function(){

	
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
            return validateForm();
                          
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
            //jobb: "required",
			//lastname: "required",
			/*email: {
				required: true,
				email: true
			}*/
		

		},
		messages: {
            carYear: "Você deve informar o ano do veículo",
            carModel: "Você deve informar o veículo",
			mediaKM: "Você deve informar a média de KM",
			placa: "Você deve informar a Placa",
			chassi: "Você deve informar o Chassi",
			renavam: "Você deve informar o Renavam",
            cor: "Você deve informar a Cor",
            rua: "Você deve informar a Rua",
            numero: "Você deve informar o Número",
            cidade: "Você deve informar a Cidade",
            estado: "Você deve informar o Estado",
            nomesegurado: "Você deve informar o Nome do segurado",
            datanasc: "Você deve informar a Data de nascimento",
            cpf: "Você deve informar o CPF",
            nomeempresa: "Você deve informar o Nome da empresa",
            ie: "Você deve informar o IE",
            cnpj: "Você deve informar o CNPJ",
				
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
    







