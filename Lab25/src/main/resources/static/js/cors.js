var btnSubmit = document.getElementById("btnSubmit");
btnSubmit.addEventListener("click", procesar );

var url = "https://agile-plains-29293.herokuapp.com";

function procesar() {
    console.log("Procesando ......");
    req = new XMLHttpRequest();

  req.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
     document.getElementById("rpta").innerHTML = this.responseText;
    }
  };
  req.open("GET", url+"/api/v1/listarProductos", true);
  req.send();    
  
}