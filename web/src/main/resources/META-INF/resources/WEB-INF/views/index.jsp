<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bookiez Bet</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="/resources/images.icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">

            <div class="table100 ver2 m-b-110">
                <table data-vertable="ver2">
                    <thead>
                    <tr class="row100 head">
                        <th class="column100 column1" data-column="column1"></th>
                        <th class="column100 column2" data-column="column2">1</th>
                        <th class="column100 column3" data-column="column3">X</th>
                        <th class="column100 column4" data-column="column4">2</th>
                        <th class="column100 column5" data-column="column5">GG</th>
                        <th class="column100 column6" data-column="column6">3+</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${games}" var="game">
                        <tr class="row100">
                            <td class="column100 column1" data-column="column1">${game.homeTeam.name} - ${game.awayTeam.name}</td>
                            <td class="column100 column1" data-column="column1">${game.odds[0].odds}</td>
                            <td class="column100 column1" data-column="column1">${game.odds[1].odds}</td>
                            <td class="column100 column1" data-column="column1">${game.odds[2].odds}</td>
                            <td class="column100 column1" data-column="column1">${game.odds[3].odds}</td>
                            <td class="column100 column1" data-column="column1">${game.odds[4].odds}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>