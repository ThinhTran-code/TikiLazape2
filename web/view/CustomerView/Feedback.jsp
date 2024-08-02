<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Ansonika">
        <title>FeedBack Product</title>

        <!-- Favicons-->
        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" type="image/x-icon" href="img/apple-touch-icon-57x57-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="72x72" href="img/apple-touch-icon-72x72-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="114x114" href="img/apple-touch-icon-114x114-precomposed.png">
        <link rel="apple-touch-icon" type="image/x-icon" sizes="144x144" href="img/apple-touch-icon-144x144-precomposed.png">

        <!-- GOOGLE WEB FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

        <!-- BASE CSS -->
        <c:url value="CSS/bootstrap.min.css" var="bootstrap"/>
        <c:url value="CSS/style113.css" var="style"/>
        <link href="${bootstrap}" rel="stylesheet" type="text/css"/>
        <link href="${style}" rel="stylesheet" type="text/css"/>

        <!-- SPECIFIC CSS -->
        <c:url value="/CSS/leave_review.css" var="leave_review"/>
        <link href="${leave_review}" rel="stylesheet" type="text/css"/>

        <!-- YOUR CUSTOM CSS -->
        <link href="css/custom.css" rel="stylesheet">

        <%@include file="../../Template/libheader.jsp" %>
    </head>

    <body>
        <%@include file="../../Template/navbar.jsp" %>
        <div id="page">

            <main>
                <div class="container margin_60_35">
                    <div class="row justify-content-center">
                        <div class="col-lg-8">
                            <div class="write_review">
                                <h1>Write a review </h1>
                                <form action="feedbackproduct" method="post">
                                    <input type="hidden" name="orderDetai_id" value="${param.orderDetai_id}" />
                                    

                                    <div class="rating_submit">
                                        <div class="form-group">
                                            <label class="d-block">Overall rating</label>
                                            <span class="rating mb-0">
                                                <input type="radio" class="rating-input" id="5_star" name="rating-input" value="5"><label for="5_star" class="rating-star"></label>
                                                <input type="radio" class="rating-input" id="4_star" name="rating-input" value="4"><label for="4_star" class="rating-star"></label>
                                                <input type="radio" class="rating-input" id="3_star" name="rating-input" value="3"><label for="3_star" class="rating-star"></label>
                                                <input type="radio" class="rating-input" id="2_star" name="rating-input" value="2"><label for="2_star" class="rating-star"></label>
                                                <input type="radio" class="rating-input" id="1_star" name="rating-input" value="1"><label for="1_star" class="rating-star"></label>
                                            </span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Your review</label>
                                        <textarea id="review-text" name="feedback" class="form-control" style="height: 180px;" placeholder="Write your review..."></textarea>
                                    </div>
                                    <!-- 
                                    <div class="form-group">
                                        <label>Add your photo (optional)</label>
                                        <div class="fileupload"><input type="file" name="fileupload" accept="image/*"></div>
                                    </div>
                                    -->
                                    <div class="form-group">
                                        <div class="checkboxes float-left add_bottom_15 add_top_15">
                                            <label class="container_check"> Confirm your order review
                                                <input type="checkbox" name="confirm_review">
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                    </div>
                                    <button type="submit" id="submit-review" class="btn_1">Submit review</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </main>
            <!--/main-->

            <!--/footer-->
        </div>
        <!-- page -->

        <div id="toTop"></div><!-- Back to top button -->

        <!-- COMMON SCRIPTS -->
        <script src="JS/common_scripts.min.js"></script>
        <script src="JS/main.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                const stars = document.querySelectorAll('.rating-star');
                stars.forEach(star => {
                    star.addEventListener('click', function() {
                        stars.forEach(s => s.classList.remove('checked'));
                        this.classList.add('checked');
                        const input = this.previousElementSibling;
                        input.checked = true;
                    });
                });
            });

            document.getElementById('submit-review').addEventListener('click', function() {
                var reviewText = document.getElementById('review-text').value.trim();
                if (reviewText === "") {
                    alert('Please enter your review');
                    return false; // Prevent form submission
                } else {
                    alert('Successfully');
                }
            });
        </script>

    </body>
</html>
