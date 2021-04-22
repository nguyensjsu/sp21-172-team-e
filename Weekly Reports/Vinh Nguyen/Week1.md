# Week 1 Notes for Vinh Nguyen

### self notes
* Issue 1: Having issues with linking an image from a different path
    * **Solution: Thymeleaf processes images in static/images and we use th:src="@{/images/starbuckslogo.png} to access image**