searchVisible = 0;
transparent = true;
xVeiculoCarregado = false;

$(document).ready(function(){
	// xCodigoLoc - C�digo da localiza��o, ser� salvo na cota��o ao final.
	// xCodigoSegurado - C�digo do segurado, ser� salvo na cota��o ao final.
	// xCodigoCotacao - C�digo da cota��o corrente, usado para fazer UPDATE no final

	xValorCarro = 0;
	xValorVidros = 0; 
	xValorFranquia = 0;
	xValorServicos = 0;

		$("#add_row").click(function(){
			addDriverLine();
		});
	//http://bootsnipp.com/snippets/featured/dynamic-table-row-creation-and-deletion	

    /*  Activate the tooltips      */
    $('[rel="tooltip"]').tooltip();
      
    $('.wizard-card').bootstrapWizard({
        'tabClass': 'nav nav-pills',
        'nextSelector': '.btn-next',
        'searchSelector': '.btn-change',
        'newSelector': '.btn-info',
        'previousSelector': '.btn-previous',
         
         onInit : function(tab, navigation, index){
        	 carregaMarcas();
            
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
        	
	            if (index == 2){ // Localiza��o
	                saveLocalizacao();
	                //alert('Os dados de localiza��o foram salvos.');
	            } else {
	            	if (index == 3){ // Segurado 
	            		//alert(xCodigoLoc);
		                saveSegurado();		
		                saveCotacao(xCodigoLoc, xCodigoSegurado);
	            		$('.btn-next').attr("disabled", !xVeiculoCarregado);
		                //alert('Os dados do segurado foram salvos.');
	            	} else {
		            	if (index == 4){ // Ve�culo 
		            		//alert('aaaa');
		            		saveVeiculo();
		            	} else {
		            		if (index == 5){ 
		            			if (validaCondutores()){
		            				saveCondutores();
		            			} else {
		            				alert('Você deve informar todos os campos dos condutores');
		    	            		return false;
		            			}	
			            		//alert(xCodigoSegurado);
			            	}
		            	}
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
			$('.btn-newCar').hide();
			            
            var wizard = navigation.closest('.wizard-card');

            if($current == 1) {
                $(wizard).find('.btn-next').hide();
				$('.btn-previous').hide();
            } else { 					
				if($current == 2) {
					$('.btn-previous').hide();
					$(wizard).find('.btn-next').show();
					var codcotacaoSelecionada = $("input[name='pesqOpt']:checked").val();
					if (codcotacaoSelecionada > -1){
						//alert('Alterando cotação: ' + codcotacaoSelecionada);
						carregarCotacaoParaCampos(codcotacaoSelecionada);
					}else{
						novaCotacao();						
					}
						
				} else {
					$('.btn-previous').show();
					// If it's the last tab then hide the last button and show the finish instead
					if($current >= $total) {
						$(wizard).find('.btn-next').hide();
						$(wizard).find('.btn-finish').show();
						$(wizard).find('.btn-save').show();
						carregaSegurado(xCodigoSegurado);
						carregaLocalizacao(xCodigoLoc);
						carregaValores();
						carregaDetalheVeiculos();
					} else {
						$(wizard).find('.btn-next').show();
						$(wizard).find('.btn-finish').hide();
						$(wizard).find('.btn-save').hide();
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
    
    $("select[name='carBrand']").change(function(){
    	alterarMarca();
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
    
    $('.btn-save').click(function(){	
		//alert(xCodigoSegurado);
		//alert(xCodigoLoc);
    	saveCotacao(xCodigoLoc, xCodigoSegurado);
    	alert('A cotação foi salva com sucesso!');

		location.reload();
    });
    
    $("input[name='Pesquisar']").click(function(){
    	//alert('aaa');
		pesquisar();
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
		$('#anomodelo01').val($('.fabYear').find(":selected").text());
		var xYear = parseInt($('.fabYear').find(":selected").text()) + 1;
		$('.anomodelo02').text("  "+xYear);
		$('#anomodelo02').val(xYear);
    });

    $('.modelYear').change(function(){
        $('.aftermodelyear').fadeIn('slow'); 
        carregaModelosParaCombo();	
    });
    
    $('.carroreserva').change(function(){
    	xValor = 0;
    	xValorBase = $('.valorbase').text();
    	if ($(".carroreserva:checked").val() == "7"){
        	xValor = xValorBase * 0.05;
        	$('.valorCarro').text(" + R$" + parseFloat(xValor).toFixed(2));    		
    	}else{
    		if ($(".carroreserva:checked").val() == "15"){
            	xValor = xValorBase * 0.15;
            	$('.valorCarro').text(" + R$" + parseFloat(xValor).toFixed(2));
    		} else {	
    			$('.valorCarro').text(" + R$0");
    		}	
    	}    	

    	xValorCarro = xValor;
    	atualizaValores();    	
    }); 
    
    $('.vidros').change(function(){
    	xValor = 0;
    	if ($(".vidros:checked").val() == "S"){
        	xValorBase = $('.valorbase').text();
        	xValor = xValorBase * 0.05;
        	$('.valorVidros').text(" + R$" + parseFloat(xValor).toFixed(2));    		
    	}else{
    		$('.valorVidros').text(" + R$0");
    	}

    	xValorVidros = xValor;
    	atualizaValores();    	
    });
    
    $('.franquia').change(function(){
    	xValor = 0;
    	xValorBase = $('.valorbase').text();
    	xTotalFranquia = xTotalFranquiaOriginal;
    	if ($(".franquia:checked").val() == "R"){
        	xValor = xValorBase * 0.05;
        	xTotalFranquia = xTotalFranquia * 0.85;
        	$('.valorFranquia').text(" + R$" + parseFloat(xValor).toFixed(2));    		
    	}else{
    		if ($(".franquia:checked").val() == "50"){
            	xValor = xValorBase * 0.15;
            	xTotalFranquia = xTotalFranquia * 0.50;
            	
            	$('.valorFranquia').text(" + R$" + parseFloat(xValor).toFixed(2));
    		} else {	
    			$('.valorFranquia').text(" + R$0");
    		}	
    	}

    	xValorFranquia = xValor;
    	atualizaValores();    	
    	
    	
    	$('.valortotalfranquia').text(parseFloat(xTotalFranquia).toFixed(2));
    });  
    
    $('.servicos24').change(function(){
    	xValor = 0;
    	if ($(".servicos24:checked").val() == "S"){
        	xValorBase = $('.valorbase').text();
        	xValor = xValorBase * 0.05;
        	$('.valorServicos').text(" + R$" + parseFloat(xValor).toFixed(2));    		
    	}else{
    		$('.valorServicos').text(" + R$0");
    	}

    	xValorServicos = xValor;
    	atualizaValores();    	
    });
	
	$("select[name='carModel']").change(function(){        
		$('.btn-next').attr("disabled", false);
		if (xTipoPessoa == 'pj'){
			$('.btn-newCar').show();
		}	
	});
    
    $height = $(document).height();
    $('.set-full-height').css('height',$height);
    $('.image-container').css('height','100%');

    $(".btn-newCar").click(function(){
    	if (validateForm()){
    		saveVeiculo();
    		limparVeiculo();
			$('.btn-newCar').hide();
    	}
    });
    
    $(".btn-finish").click(function(){
    	saveApolice();
    	alert("Apólice gerada com sucesso.");
		location.reload();    	
    });
    
    $(".btn-edtQuote").click(function(){
    	alert("CLICADO");
    });    
});

$(document).on('click', '.pesqOpt', function(){
	$("input[name='Alterar']").attr("disabled", false);
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

function novaCotacao(){
	//alert('gerando cota��o');
	xReturn = httpGet("http://localhost:8080/susegbackend/NovaCotacao");
	//alert(xReturn);
	
	xCotacaoSaved = eval ("(" + xReturn + ")");	
	xCodigoCotacao = xCotacaoSaved.codigo;
	//alert(xCodigoCotacao);
	//xCodigoCotacao
}

function validaCondutores(){
	isValid = true;
	i = 0;
	found = $('#addr'+i).html() != "";
	while (found){
		xName = $('#addr'+i).find("input[name='name" + i + "']").val();
		xCPF  = $('#addr'+i).find("input[name='cpf" + i + "']").val();
		
		if (xName == '' || xCPF == ''){
			isValid = false;
		}		
		
		i++;
		found = $('#addr'+i).html() != "";	
	}
	
	return isValid;
}

function saveCondutores(){
	i = 0;
	found = $('#addr'+i).html() != "";
	while (found){
		var xCondNome   = $("input[name='name" + i + "']").val();
		var xCondCPF    = $("input[name='cpf" + i + "']").val();
		var xCondIdade  = $("input[name='idade" + i + "']").val();
		var xCondSexo   = $("input[name='sexo" + i + "']:checked").val();
		var xCondFilhos = $("input[name='filho" + i + "']:checked").val();
		var xCondCasado = $("input[name='casado" + i + "']:checked").val();
		var xCondCodigo = $("span[name='spanCodCond" + i + "']").val();
		
		params = 'nome=' + xCondNome + '&cpf=' + xCondCPF + '&idade=' + xCondIdade + '&sexo=' + xCondSexo + '&temFilho=' + xCondFilhos + '&casado=' + xCondCasado + '&cotacao=' + xCodigoCotacao;
		if (xCondCodigo !== ''){
			params = params + '&codigo=' + xCondCodigo;
		}
		
		//GravaCondutor?nome=Paulo&cpf=456.789.123-20&idade=27&sexo=M&temFilho=N&casado=S&cotacao=1
		
		xReturn = httpGet("http://localhost:8080/susegbackend/GravaCondutor?" + params);
		//alert(xReturn);
		
		i++;
		found = $('#addr'+i).html() != "";	
	}
}

function alterarMarca(){
	if ($(".carBrand option:selected").text() == 'Selecione'){
		$('.fabYear').fadeOut('slow');
	} else {
		$('.fabYear').fadeIn('slow');
	}	
	$(".fabYear option").filter(function() {
		return this.text == "Selecione"; 
	}).attr('selected', true);
    $('.modelYear').fadeOut('slow');   		
    $('.aftermodelyear').fadeOut('slow');   
    $('.btn-next').show();
	$('.btn-next').attr("disabled", true);
}

function limparVeiculo(){
	$(".carBrand option").filter(function() {      
		return this.text == "Selecione"; 
	}).attr('selected', true);	
	alterarMarca();
	
	// Limpa os campos que ficaram invisíveis
	$("select[name='carModel'] option").filter(function() {      
		return this.text == "Selecione"; 
	}).attr('selected', true);	
	$("input[name='placa']").val('');
	$("input[name='chassi']").val('');
	$("input[name='renavam']").val('');
	$("input[name='cor']").val('');
	$("input[name='mediaKM']").val('');
}

function saveVeiculo(){
	xMarca    = $(".carBrand option:selected").val();
	xAnoFab   = $(".fabYear option:selected").val();
	xAnoModel = $("input[name='anomodelo']:checked").val();
	xCodFipe  = $("select[name='carModel'] option:selected").val();
	xPlaca    = $("input[name='placa']").val();
	xChassi   = $("input[name='chassi']").val();
	xRenavam  = $("input[name='renavam']").val();
	xCor      = $("input[name='cor']").val();
	xMediaKM  = $("input[name='mediaKM']").val();
	xCodVei   = $('#codigoVeiculoCarregado').val();
	
	params = "anofabricacao=" + xAnoFab + "&anomodelo=" + xAnoModel + "&chassi=" + xChassi + "&cor=" + xCor + "&mediakmmes=" + xMediaKM + "&placa=" + xPlaca + "&renavam=" + xRenavam + "&marca=" + xMarca + "&cotacao=" + xCodigoCotacao + "&codigofipe=" + xCodFipe;
	
	if (typeof xCodVei !== 'undefined'){
		params = params + '&codigo=' + xCodVei;
	}
	
	//GravaVeiculo?anofabricacao=2013&anomodelo=2014&chassi=12345678&cor=Preto&mediakmmes=500&modelo=Celta&placa=ABC-1234&renavam=abc123&idcotacao=1&marca=GM&cotacao=1
	//alert(params);
	xReturn = httpGet("http://localhost:8080/susegbackend/GravaVeiculo?" + params);
	//alert(xReturn);
}

function saveCotacao(){
	xValorCotacao  = $(".valortotal").text();
	if (xValorCotacao == ""){
		xValorCotacao = 0;
	}
		
	params = "codigoCotacao=" + xCodigoCotacao + "&valorCotacao=" + xValorCotacao + "&codSegurado=" + xCodigoSegurado + "&codLocalizacao=" + xCodigoLoc;
	//alert(params);
	xReturn = httpGet("http://localhost:8080/susegbackend/GravaCotacao?" + params);
	//alert(xReturn);
}

function saveSegurado(){
	xBonus = 0;
	xTipoPessoa = $("input[name='tipopessoa']:checked").val();
	if (xTipoPessoa == "pf"){
		var xNomeSegurado = $("input[name='nomesegurado']").val();
		var xDataNasc     = $("input[name='datanasc']").val();
		var xCPF          = $("input[name='cpf']").val();
		var xTelefone     = $("input[name='telefone']").val();
		var xSexo         = $("input[name='sexo']:checked").val();
		xBonus            = $("input[name='classebonus']").val();
		
		xDataNasc.replace('/', '%2F');		
		params = "nome=" + xNomeSegurado + "&cpf=" + xCPF + "&dataNascimento=" + xDataNasc + "&telefone=" + xTelefone + "&sexo=" + xSexo + "&bonus=" + xBonus;
	} else {
		if (xTipoPessoa == "pj"){
			var xNomeEmpresa = $("input[name='nomeempresa']").val();
			var xIE          = $("input[name='ie']").val();
			var xCNPJ        = $("input[name='cnpj']").val();
			xBonus           = $("input[name='classebonusCNPJ']").val();
			var xTelefone    = $("input[name='telefonePJ']").val();

			params = "nome=" + xNomeEmpresa + "&cnpj=" + xCNPJ + "&ie=" + xIE + "&bonus=" + xBonus + "&telefone=" + xTelefone;
		}
	}
	
	if (typeof xCodigoSegurado !== 'undefined'){
		params = params + '&codigo=' + xCodigoSegurado;
	}

	//nome=Paulo&cpf=456.789.123-20&dataNascimento=08%2F09%2F1988&telefone=3332-3344&sexo=M
	xReturn = httpGet("http://localhost:8080/susegbackend/GravaSegurado?" + params);
	
	// { codigo: 8, nome: 0, cpf: 0, sexo: M, telefone: 0, dataNascimento: 01/01/1980  }
	xSegSaved = eval ("(" + xReturn + ")");	
    xCodigoSegurado = xSegSaved.codigo;
    //alert(xCodigoSegurado);
    
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
	if (typeof xCodigoLoc !== 'undefined'){
		params = params + '&codigo=' + xCodigoLoc;
	}
    xReturn = httpGet("http://localhost:8080/susegbackend/GravaLocalizacao?" + params);
    
    // { codigo: 6, rua: Teste, numero: 999, cep: 900312, cidade: Blu, estado: SC, pais: Brasil  }
    xLocSaved = eval ("(" + xReturn + ")"); 
    xCodigoLoc = xLocSaved.codigo;       
    
    return xReturn; 
}

function saveApolice(){
	
	xReturn = httpGet("http://localhost:8080/susegbackend/GravaApolice?codigoCotacao=" + xCodigoCotacao);
	
}

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
    
function carregaSegurado(aCodigoSegurado){
	xCodigoSegurado = aCodigoSegurado;
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaSegurado?codigoSegurado=" + aCodigoSegurado);
	//alert(xReturn);
	
	xSegCarregado = eval ("(" + xReturn + ")");
	//alert(xSegCarregado.codigo);

	$(".nomeSegurado").text(xSegCarregado.nome);
	$(".cpfSegurado").text(xSegCarregado.cpf);
	$(".sexoSegurado").text(xSegCarregado.sexo);
	$(".dataNascSegurado").text(xSegCarregado.dataNascimento);
	$(".telefoneSegurado").text(xSegCarregado.telefone);
	
	
	if (xSegCarregado.cpf != null){
		$("input[name='tipopessoa'][value='pf']").prop('checked', true);
		
		$("input[name='nomesegurado']").val(xSegCarregado.nome);
		$("input[name='datanasc']").val(xSegCarregado.dataNascimento);
		$("input[name='cpf']").val(xSegCarregado.cpf);
		$("input[name='telefone']").val(xSegCarregado.telefone);
		if (xSegCarregado.sexo == 'M'){
			$("input[name='sexo'][value='M']").prop('checked', true);
		}else{
			if (xSegCarregado.sexo == 'F'){
				$("input[name='sexo'][value='F']").prop('checked', true);
			}	
		}
		$("input[name='classebonus']").val(xSegCarregado.classeBonus);		
	}else{
		if (xSegCarregado.cnpj != null){
            $('.pessoafisicaform').fadeOut('fast');
            setTimeout(function() {$('.pessoajuridicaform').fadeIn('fast');}, 300);			
			
			$("input[name='tipopessoa'][value='pj']").prop('checked', true);
			$("input[name='nomeempresa']").val(xSegCarregado.nome);
			$("input[name='ie']").val(xSegCarregado.inscricaoestadual);
			$("input[name='cnpj']").val(xSegCarregado.cnpj);
			$("input[name='classebonusCNPJ']").val(xSegCarregado.classeBonus);
			
		}
	}
	
}
    
function carregaLocalizacao(aCodigoLoc){
	xCodigoLoc = aCodigoLoc;
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaLocalizacao?codigoLocalizacao=" + aCodigoLoc);
	//alert(xReturn);
	
	xLocCarregado = eval ("(" + xReturn + ")");
	//alert(xLocCarregado.rua);

	$(".endLocalizacao").text("Rua: " + xLocCarregado.rua + ", " + xLocCarregado.numero + ", " + xLocCarregado.cep);
	$(".cidadeLocalizacao").text(xLocCarregado.cidade);
	$(".estadoLocalizacao").text(xLocCarregado.estado);
	
	//alert('Carregar valores');
	$("input[name='rua']").val(xLocCarregado.rua);
	$("input[name='cidade']").val(xLocCarregado.cidade);
	$("input[name='numero']").val(xLocCarregado.numero);
	$("input[name='estado']").val(xLocCarregado.estado);
	$("input[name='cep']").val(xLocCarregado.cep);
	$("input[name='pais']").val(xLocCarregado.pais);	
}

function carregaCondutores(aCodCotacao){
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaCondutores?codigo=" + aCodCotacao);
	//alert(xReturn);
	var xCondutoresCarregados = JSON.parse(xReturn);

	//alert(xCondutoresCarregados.condutores.length);
	for (var i = 0, len = xCondutoresCarregados.condutores.length; i < len; ++i) {
		var xCondutor = xCondutoresCarregados.condutores[i];

		if (i > 0){
			addDriverLine();
		}		
		$("span[name='spanCodCond" + i + "']").val(xCondutor.codigo);
		$("input[name='name" + i + "']").val(xCondutor.nome);
		$("input[name='cpf" + i + "']").val(xCondutor.cpf);
		$("input[name='idade" + i + "']").val(xCondutor.idade);
		if (xCondutor.sexo == 'M'){
			$("input[name='sexo" + i + "'][value='M']").prop('checked', true);
		}else{
			if (xCondutor.sexo == 'F'){
				$("input[name='sexo" + i + "'][value='F']").prop('checked', true);
			}	
		}
		if (xCondutor.filho == 'S'){
			$("input[name='filho" + i + "'][value='S']").prop('checked', true);
		}else{
			if (xCondutor.filho == 'N'){
				$("input[name='filho" + i + "'][value='N']").prop('checked', true);
			}	
		}	
		if (xCondutor.casado == 'S'){
			$("input[name='casado" + i + "'][value='S']").prop('checked', true);
		}else{
			if (xCondutor.casado == 'N'){
				$("input[name='casado" + i + "'][value='N']").prop('checked', true);
			}	
		}		
	}
}

function carregaVeiculo(aCodCotacao){
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaVeiculos?cotacao=" + aCodCotacao);
	xVeiculoCarregado = true;
	var obgVeiculos = JSON.parse(xReturn);
	
	//$(".detalhesValores").html('');
	for (var i = 0, len = obgVeiculos.veiculos.length; i < len; ++i) {
		var veiculo = obgVeiculos.veiculos[i];
		
		$('#codigoVeiculoCarregado').val(veiculo.codigo);
		$(".carBrand option").filter(function() {
			return this.text == veiculo.marca; 
		}).attr('selected', true);
		$(".fabYear option").filter(function() {
			return this.text == veiculo.anoFabricacao; 
		}).attr('selected', true);    
		
		$('.anomodelo01').text("  "+$('.fabYear').find(":selected").text());
		$('#anomodelo01').val($('.fabYear').find(":selected").text());
		var xYear = parseInt($('.fabYear').find(":selected").text()) + 1;
		$('.anomodelo02').text("  "+xYear);
		$('#anomodelo02').val(xYear);		
		$("input[value='" + veiculo.anoModelo + "']").attr('checked','true');
		
		carregaModelosParaCombo();		
		$("select[name='carModel'] option").filter(function() {
			return this.text == veiculo.modelo; 
		}).attr('selected', true);

		$("input[name='placa']").val(veiculo.placa);
		$("input[name='chassi']").val(veiculo.chassi);
		$("input[name='cor']").val(veiculo.cor);
		$("input[name='renavam']").val(veiculo.renavam);
		$("input[name='mediaKM']").val(veiculo.mediaKMMes);
		
		$('.fabYear').show();
	    $('.modelYear').show();   		
	    $('.aftermodelyear').show();
	}
}

function carregarCotacaoParaCampos(aCodCotacao){
	//alert('Agora vamos carregar cotação: ' + aCodCotacao);
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaCotacao?codigo=" + aCodCotacao);
	//alert(xReturn);
	xCotacao = eval ("(" + xReturn + ")");	
	xCodigoCotacao = xCotacao.codigo;
	
	//alert(xCotacao.codigoLoc);
	carregaLocalizacao(xCotacao.codigoLoc);
	carregaSegurado(xCotacao.codigoSeg);
	carregaCondutores(aCodCotacao);
	carregaVeiculo(aCodCotacao);

}

function carregaDetalheVeiculos(){
	
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaVeiculos?cotacao=" + xCodigoCotacao);
	xVeiculoCarregado = true;
	var obgVeiculos = JSON.parse(xReturn);
	
	if (obgVeiculos.veiculos.length < 2){
		var veiculo = obgVeiculos.veiculos[0];
		
		$(".descVeiculo").text(veiculo.modelo);
		$(".descAnoFab").text(veiculo.anoFabricacao);
		$(".descAnoModel").text(veiculo.anoModelo);
		$(".descPlaca").text(veiculo.placa);
		$(".descChassi").text(veiculo.chassi);
		
	}else{
		$(".detalhe-veiculos").html('');		
		xTable = "<div id='no-more-tables'>" + 
		"<table class='col-md-10 col-sm-offset-1 table-bordered table-striped table-condensed cf' style='margin-bottom: 15px;'>" +
			"<thead class='cf'>" +
				"<tr>" +
					"<th>Modelo</th>" +
					"<th>Fabricação</th>" + 
					"<th>Ano Modelo</th>" +
					"<th>Placa</th>" +
					"<th>Chassi</th>" +
					//"<th>Opções</th>" +
				"</tr>" +
			"</thead>";
		
		for (var i = 0, len = obgVeiculos.veiculos.length; i < len; ++i) {
			var veiculo = obgVeiculos.veiculos[i];
			
			xDiv = 
				"<tbody>" +
					"<tr>" +				
						"<td data-title='Modelo'>"+ veiculo.modelo +"</td>" +
						"<td data-title='Fabricação'>"+ veiculo.anoFabricacao +"</td>" +
						"<td data-title='Ano Modelo'>"+ veiculo.anoModelo +"</td>" + 
						"<td data-title='Placa'>"+ veiculo.placa +"</td>" +
						"<td data-title='Chassi'> "+ veiculo.chassi +"</td>" +
					"</tr>" +
				"</tbody>";
				
			xTable = xTable + xDiv;
		}				
			
		xTable = xTable +
			"</table>" +			
		"</div>";
			
		$(".detalhe-veiculos").html(xTable);				
	}

}

function carregaValores(){	
	xReturn = httpGet("http://localhost:8080/susegbackend/CalculaValoresPremio?codigoCotacao=" + xCodigoCotacao);
	
	//xReturn = "{\"valores\":[{\"descricao\":\"Valor base do premio\",\"valor\":\"1050.0\"},{\"descricao\":\"Perfil do condutor\",\"valor\":\"115.5\"},{\"id\":\"1\", \"descricao\":\"Valor do premio\",\"valor\":\"1165.5\"}]}";
	
	//alert(xReturn);
	var obj = JSON.parse(xReturn);
	
	var xValorPremio = 0;
	$(".detalhesValores").html('');
	for (var i = 0, len = obj.valores.length; i < len; ++i) {
		var xDetalhe = obj.valores[i];
		
		if (xDetalhe.id == 1){ // É valor do premio
			xValorPremio = xDetalhe.valor;
		}
		
		classe = '';
		if (xDetalhe.id == 2){ // É valor da franquia
			xTotalFranquiaOriginal = xDetalhe.valor;
			classe = "class='valortotalfranquia'";
		}
		
		xDiv = "<div class='col-sm-12 col-sm-offset-3'>";
		xDiv = xDiv + "<b>" + xDetalhe.descricao + "</b>";
		xDiv = xDiv + ": R$<span " + classe + ">" + parseFloat(xDetalhe.valor).toFixed(2); + "</span>";
		xDiv = xDiv + "</div>";

		$(".detalhesValores").html($(".detalhesValores").html() + xDiv);
	}

	//alert(xBonus);
	if (xBonus > 0){
		//alert(xValorPremio);
		xDescBonus = (xBonus * 0.03) * xValorPremio;
		//alert(xDescBonus);
		xValorPremio = xValorPremio - xDescBonus;
		//alert(xValorPremio);
		xDiv = "<div class='col-sm-12 col-sm-offset-3'>";
		xDiv = xDiv + "<b>Desconto classe de bônus: </b>";
		xDiv = xDiv + ": R$" + parseFloat(xDescBonus).toFixed(2);
		xDiv = xDiv + "</div>";
		$(".detalhesValores").html($(".detalhesValores").html() + xDiv);
	}	
	
	$(".valorbase").text(xValorPremio);
	$(".valortotal").text(xValorPremio);
	atualizaValores();
	
	/*
	
	{"valores":[{"descricao":"Valor base do pr�mio","valor":"1050.0"},
	            {"descricao":"Perfil do condutor","valor":"115.5"},
	            {"descricao":"Valor do pr�mio","valor":"1165.5"}]}

	$(".valorbase").text(obj.valores[3].valor);
	$(".valortotal").text(obj.valores[3].valor);
	
	$(".basedetalhe").text(obj.valores[0].descricao);
	$(".valorbasedetalhe").text(parseFloat(obj.valores[0].valor).toFixed(2));
	$(".perfil").text(obj.valores[1].descricao);
	$(".valorperfil").text(parseFloat(obj.valores[1].valor).toFixed(2));
	$(".10anos").text(obj.valores[2].descricao);
	$(".valor10anos").text(parseFloat(obj.valores[2].valor).toFixed(2));
	$(".premiodetalhe").text(obj.valores[3].descricao);
	$(".valorpremiodetalhe").text(parseFloat(obj.valores[3].valor).toFixed(2));*/
}

function atualizaValores(){
	xValorBase = $('.valorbase').text();
	xValorTotal = parseFloat(xValorCarro) + parseFloat(xValorVidros) + parseFloat(xValorFranquia) + parseFloat(xValorServicos) + parseFloat(xValorBase);
	
	$('.valortotal').text(parseFloat(xValorTotal).toFixed(2));
	$('.parcela1').text(parseFloat(xValorTotal).toFixed(2));
	$('.parcela2').text(parseFloat(xValorTotal/2).toFixed(2));
	$('.parcela3').text(parseFloat(xValorTotal/3).toFixed(2));
	$('.parcela4').text(parseFloat(xValorTotal/4).toFixed(2));
}

function editQuote(codigo){
	alert(codigo);
}


var i=1;
function addDriverLine(){
	//$('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='idade"+i+"' type='text' placeholder='Idade'  class='form-control input-md' max='99' min='0'></td><td><input  name='sexo"+i+"' type='radio' value='M' checked style='margin-top: 13px'> Masculino <input  name='sexo"+i+"' type='radio' value='F'> Feminino</td>");
	$('#addr'+i).html("<td>"+ (i+1) +"<span name='spanCodCond"+i+"' hidden></td><td style='width: 210px'></span><input type='text' name='name"+i+"' placeholder='Name' class='form-control' size='60'/></td><td><input type='text' name='cpf"+i+"' placeholder='000.000.000-00' class='form-control' size='60' maxlength='14'/></td><td style='width: 85px'><input type='number' name='idade"+i+"' placeholder='Idade' class='form-control' max='99' min='18' value='18'/></td><td style='width: 100px'><input type='radio' name='sexo"+i+"' value='M' checked style='margin-top: 0px'/> Masculino <br><input type='radio' name='sexo"+i+"' value='F' /> Feminino</td><td style='width: 80px'><input type='radio' name='filho"+i+"' value='S' checked style='margin-top: 0px'/> Sim <br><input type='radio' name='filho"+i+"' value='N' /> Não</td><td style='width: 80px'><input type='radio' name='casado"+i+"' value='S' checked style='margin-top: 0px'/> Sim <br><input type='radio' name='casado"+i+"' value='N' /> Não</td>");
			
	
	$('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
	i++; 

	$("#delete_row").click(function(){
		if(i>1){
			$("#addr"+(i-1)).html('');
			i--;
		}
	});	
}

function pesquisar(){	
	
	xTable = "<div id='no-more-tables'>" + 
	"<table class='col-md-10 col-sm-offset-1 table-bordered table-striped table-condensed cf' style='margin-bottom: 15px;'>" +
		"<thead class='cf'>" +
			"<tr>" +
				"<th></th>" +
				"<th>Código</th>" + 
				"<th class='date'>Data Criação</th>" +
				"<th class='date'>Vencimento</th>" +
				"<th class='numeric'>Valor</th>" +
				//"<th>Opções</th>" +
			"</tr>" +
		"</thead>";
		
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaCotacoes?valor=" + $("input[name='valorpesquisar']").val());
	//alert(xReturn);
	var obj = JSON.parse(xReturn);

	
	//{"cotacoes":[{ "codigo": "260", "comissao": "0.0", "dataCriacao": "30/11/2015", "valor": "0.0", "vigencia": "30/11/2016", "codigoSeg": "0", "codigoLoc": "0"  }]}
	
	
	$('#result-table').html('');
	if (obj.cotacoes.length > 0){
		for (var i = 0, len = obj.cotacoes.length; i < len; ++i) {
			var xCotacao = obj.cotacoes[i];
			
			xDiv = 
			"<tbody>" +
				"<tr>" +				
					"<td data-title='Opção' style='width: 10px'><input type='radio' class='pesqOpt' name='pesqOpt' value='" + xCotacao.codigo + "'></td>" +
					"<td data-title='Código'>"+ xCotacao.codigo +"</td>" +
					"<td data-title='Data Criação'>"+ xCotacao.dataCriacao +"</td>" + 
					"<td data-title='Vencimento' class='date'>"+ xCotacao.vigencia +"</td>" +
					"<td data-title='Valor' class='numeric'> R$"+ parseFloat(xCotacao.valor).toFixed(2) +"</td>" +
					//"<td data-title='Opções' class='numeric'>"+
					//    "<button class='btn btn-edtQuote' style='height: 25px; padding: 0px 0px;' onclick='editQuote("+xCotacao.codigo+"); return false;'>" +
					//		"<span class='glyphicon glyphicon-search text-success'></span>" +
					//	"</button>" +
					//"</td>" +
				"</tr>" +
			"</tbody>";
			
			xTable = xTable + xDiv;
		}
		
		
		xTable = xTable +
			"</table>" +			
		"</div>";
		
		$('#result-table').html(xTable);
		$('.btn-change').show();
	} else {
		alert("Nenhum resultado encontrado");
	}
}

function carregaMarcas(){
	xReturn = httpGet("http://localhost:8080/susegbackend/RetornaDadosFIPE?acao=marcas");
	
	//alert(xReturn);
	var marcas = JSON.parse(xReturn);
	
	$("select[name='carBrand']").empty().append($('<option>', {
		value: '',
		text: 'Selecione'
	}));		;
	for (var i = 0, len = marcas.marcas.length; i < len; ++i) {
		var marca = marcas.marcas[i];	
		
		$("select[name='carBrand']").append($('<option>', {
			value: marca.marca,
			text: marca.marca
		}));		
	}	
}

function carregaModelosParaCombo(){
    xMarca = $('.carBrand').find(":selected").text();
    xAnoModelo = $("input[name='anomodelo']:checked").val();
    xReturn = httpGet("http://localhost:8080/susegbackend/RetornaDadosFIPE?acao=veiculos&marca=" + xMarca + "&anomodelo=" + xAnoModelo);    	
	//alert(xReturn);
	var modelos = JSON.parse(xReturn);
	
	$("select[name='carModel']").empty().append($('<option>', {
		value: '',
		text: 'Selecione'
	}));				
	for (var i = 0, len = modelos.veiculos.length; i < len; ++i) {
		var veiculo = modelos.veiculos[i];	
		
		$("select[name='carModel']").append($('<option>', {
			value: veiculo.codigofipe,
			text: veiculo.nome
		}));		
	}
}