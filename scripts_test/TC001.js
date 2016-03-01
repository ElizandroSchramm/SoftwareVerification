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
    console.log("Salvando printscreen do site com o nome TC001_result.png no mesmo diretorio que o phantom esta");
    page.render('TC001_result.png');
  	phantom.exit();
  } else {
  	console.log("Erro ao carregar o site inicial.");
  }
});
