<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Ajout d'une Oeuvre </H1>
<form method="post" action="/insererOeuvrevente.htm" onsubmit="return teste()">
<div class="col-md-12 well well-md">
    <h1>Ajouter Séjour</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txttitre" value="" id="titrev" class="form-control" min="0">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prix de l'oeuvre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprix" value="" id="prix" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Ajouter
        </button>

        <button type="button" class="btn btn-default btn-primary"
                onclick="{
                            window.location = '../index.jsp';
                        }">
            <span class="glyphicon glyphicon-remove"></span> Annuler

        </button>
    </div>
</div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>