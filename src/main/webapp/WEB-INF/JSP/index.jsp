<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title> Accueil - Recherche site </title>
            <meta charset="UTF-">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="resources/css/index.css" rel="stylesheet"> 
            <!--<link href="index.css" rel="stylesheet">-->
            
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
            <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" rel="stylesheet">

            <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    </head> 

    <body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
    <!-- NAV BAR-->
        <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Projet Ter</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#intro">Introduction</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#recherche">Recherche</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#readme">README</a>
                    </li>
                 
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
</nav>

   <header>
        <div class="container">
            <div id="intro">
                <div class="intro-text">
                    <div class="intro-heading">Projet  ter 2016 -2017</div>
                    <div class="intro-lead-in">Agrégation de deux sources de données hétérogènes</div>
                    
                    <a href="#recherche" class="page-scroll btn btn-info">Démarrer</a>
                </div>
            </div>
        </div>
</header>
     <section id="recherche" class="recherche-section">
        <div class="container">
            <div class="row">
                 <div class="col-md-8 col-md-offset-2">
                    <h1 class="recherche-title">Bienvenue dans la ville de Lyon</h1>
                    <p class="recherche-text">
                        Lyon possède un patrimoine historique, architectural, culturel et gastronomique remarquable
                        <br />
                        La ville compte 236 monuments protégés au titre des monuments historiques et 289 lieux et monuments répertoriés à l'inventaire général du patrimoine culturel. Par ailleurs, elle compte 602 objets répertoriés à l'inventaire des monuments historiques et 247 objets répertoriés à l'inventaire général du patrimoine culturel.
                    </p>
                    <p class="recherche-text">
                        Vous faites une escale à Lyon ? Nous vous proposons une selection de site à visiter en fonction de votre periode d'escale.
                    </p>
                   
                    <a href="#about" class="btn btn-circle page-scroll">
                        <i class="fa fa-angle-double-down animated"></i>
                    </a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    	<form method ="post" action="result">
                        <!--  <form method ="post" action="result.jsp">-->
        
                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                <div id ="container_search">
                                    <div class="input-group stylish-input-group">
                                        <input type = "text" name="preferences" class="form-control" placeholder="Ex: Musée, Quartier..">
                                        <span class="input-group-addon">
                                            <button type = "submit">
                                                <span class="glyphicon glyphicon-search"></span>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                     
                        <div class ="row">

                            <div class='col-sm-2 col-sm-offset-3'>
                                <div class="form-group">
                                    <div class='input-group date' id='heureArrivee'>
                                        <input type = "text" name= "heureArrivee" class="form-control" required>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class='col-sm-2'>
                                <div class="form-group">
                                    <div class='input-group date' id="dateJour">
                                        <input data-format="dd/MM/yyyy" placeholder="DD/MM/YYY" type = "text" name= "dateJour" class="form-control" required></input>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        
                                    </div>
                                </div>
                            </div>
                            <div class='col-sm-2'>
                                <div class="form-group">
                                    <div class='input-group date' id='heureDepart'>
                                        <input data-format="hh:mm" type = "text" name= "heureDepart" class="form-control" required>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <!--<input type = "time" name= "heureArrivee" class="form-control">
                            <input type = "time" name= "heureDepart" date-min="heureArrivee">-->
                        </div>
                        <div class="row">
                            <div class="col-sm-2 col-sm-offset-5">
                                <button type="submit" class="btn btn-default">Confirmer</button>
                            </div>
                        </div>
                   </form>
                    
                </div>
            </div>
        </div>
</section>
<section id="readme" class="readme-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>README Section</h1>
                    
                </div>
            </div>
        </div>
</section>
    
        
    
    <footer>
        <section id="contact" class="id-contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="list-inline">
                        <li>
                            <a class="page-scroll" href="#intro">Home</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a class="page-scroll"href="#readme">Readme</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a class="page-scroll" href="#contact">Contact</a>
                        </li>
                    </ul>
                    <p class="copyright text-muted small">Projet TER 2016 -2017   &copy; Othomene Thibaut & Hassani Kassim</p>
                </div>
            </div>
        </div>
    </section>
</footer>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="resources/js/scroll.js"></script>
    <!--<script src="scroll.js"></script>-->
       




    </body>
</html>
