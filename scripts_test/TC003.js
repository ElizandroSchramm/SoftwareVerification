var page = require('webpage').create();

page.open('http://localhost:8080/susegbackend/', function(status) {
  console.log("Status do site: " + status);
  if(status === "success") {
  	console.log("Site abriu");
  	console.log("Clicar no botão Novo");
    page.evaluate(function() {
        var ev = document.createEvent("MouseEvent");
        ev.initEvent("click", true, true);
        document.getElementById('Novo').dispatchEvent(ev);
    });
    page.evaluate(function() {
    	console.log("Preenchendo a rua");
    	document.getElementById('rua').value = 'Avenida Paulista';
    	console.log("Preenchendo o numero");
    	document.getElementById('numero').value = '1205';
    	console.log("Preenchendo a cidade");
    	document.getElementById('cidade').value = 'BNU';
    	console.log("Preenchendo o estado");
    	document.getElementById('estado').value = 'SC';
    	console.log("Preenchendo o CEP");
    	document.getElementById('cep').value = '89110-000';
    	console.log("Preenchendo o pais");
    	document.getElementById('pais').value = 'Brasil';
    });
    console.log("Salvando printscreen do site com o nome TC003_result_1.png no mesmo diretorio que o phantom esta");
    page.render('TC003_result_1.png');
    page.evaluate(function() {
        var ev = document.createEvent("MouseEvent");
        ev.initEvent("click", true, true);
        document.getElementById('next').dispatchEvent(ev);
    });
    console.log("Salvando printscreen do site com o nome TC003_result_2.png no mesmo diretorio que o phantom esta");
    page.render('TC003_result_2.png');
  	phantom.exit();
  } else {
  	console.log("Erro ao carregar o site inicial.");
  }
});
