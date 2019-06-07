<%-- 
    Document   : testfile
    Created on : 20 déc. 2018, 15:48:53
    Author     : messi01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal-body">
            <form class="" enctype="multipart/form-data" novalidate method="post" action="UploadFileArticles?q=80691" >

                <div class="row">
                    <div class="form-group col-12 mb-2">
                        <label for="complaintinput6">Logo</label>

                        <input type="file" placeholder="signature" name="logo">

                    </div>
                </div>

                <input type="submit" class="btn btn-outline-secondary  round btn-sm admin-add-societe"
                       value="Enregistrer">
            </form>
        </div>
    </body>
</html>
