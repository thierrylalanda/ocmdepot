/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
   
   /* $(".add_commande").click(function(ev){
        ev.preventDefault();
        alert("Impossible pour le moment");
    });
    */
    $(".td_update").dblclick(function(ev){
        ev.preventDefault();
        $(this).addClass("hidden");
        $(this).parent("tr").find("td.td_to_update").removeClass("hidden");
        
        setTimeout(function(){$(".qt_update").focus();},200);
    });
    
    $(".qt_update").blur(function(e){
        e.preventDefault();
        var td=$(this).parent("td");
        td.addClass("hidden");
        td.parent("tr").find("td.td_update").removeClass("hidden");
    });
    
    $(".qt_update").keypress(function(e){
        var code=e.which || e.keyCode;//Selon le navigateur c'est which ou keyCode
        var id=$(this).attr("id");
        var val=$(this).val();
        var q="commande/addcommande";
        if(code===13){
            var client="";
            if($(this).hasClass("client")){
                client="&client="+$(this).attr("client");
                q="client/addcommande";
            }
           var form=$('<form class="form hidden" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q='+q+'&isNew=5&id='+id+client+'"></form>');
        var input=$('<input type="number" required id="quantite" value="'+val+'" name="quantite"/>');
        form.append(input);
        $("body").append(form);
        form.submit();
        }
    });
    
    //pour les propositions de commandes
    $(".td_updateP").dblclick(function(ev){
        ev.preventDefault();
       
        $(this).addClass("hidden");
         
        $(this).parent("tr").find("td.td_to_updateP").removeClass("hidden");
        
        setTimeout(function(){$(".qt_updateP").focus();},200);
       
    });
    
    $(".qt_updateP").blur(function(e){
        e.preventDefault();
        var td=$(this).parent("td");
        td.addClass("hidden");
        td.parent("tr").find("td.td_updateP").removeClass("hidden");
    });
    
    $(".qt_updateP").keypress(function(e){
        var code=e.which || e.keyCode;//Selon le navigateur c'est which ou keyCode
        var id=$(this).attr("idC");
        var val=$(this).val();
        var q="commande/traitement";
        if(code===13){
            var client="";
            if($(this).hasClass("client")){
                client="&client="+$(this).attr("client");
                q="client/addcommande";
            }
            if($(this).hasClass("q")){
                q=$(this).attr("q");
            }
           var form=$('<form class="form hidden" method="post" action="gestionCommandes?action=model&model=PasserCommandes&q='+q+'&isNew=2&idC='+id+'"></form>');
        var input=$('<input type="number" required id="quantite" value="'+val+'" name="quantite"/>');
        form.append(input);
        $("body").append(form);
        form.submit();
        }
    });
    
    if($(".autoloader").hasClass("addCommades")){
         $.ajax({
            type: "POST",
            url: 'administration?action=verifiSeuilArticle&q=jgddgg',
            data: {},
            success: function(data){
                if(data[0]){
                    $(".alert_stock").text(data[1]);
                }else{
                    $(".alert_stock").addClass("hidden");
                }
            },
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });
    }
    
   
    
});

