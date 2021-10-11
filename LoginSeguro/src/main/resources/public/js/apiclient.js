var apiclient = (function () {
    var url="https://ec2-52-91-7-111.compute-1.amazonaws.com:8000";

    function info() {

        axios.get(url+"/information").then(res=>{
            console.log(url+"/information")
            $("#information").text(res.data);
        })
    }
    function login(){
        var user={email:document.getElementById("email").value,password:document.getElementById("password").value}
        axios.post(url+"/login",user).then(res=>{
            window.location.href="authorized.html";
        })
    }

    return {
        login:login,
        info:info
    };
})();