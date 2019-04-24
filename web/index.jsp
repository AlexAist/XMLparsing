<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>XML</title>
</head>
<body>
<div class="form">
    <p class="title">Parse XML document:</p>
    <form method="post" action="banks" enctype="multipart/form-data">
        <input type="hidden" name="selector" value="parse">
        <p><label><input type="file" name="xml_file" required></label></p>
        <p class="text"><label>Select parser: <select class="element" name="parser" required>
            <option selected value="SAX">SAX</option>
            <option value="DOM">DOM</option>
            <option value="StAX">StAX</option>
        </select></label></p>
        <p><input class="element" type="submit" value="Select"/></p>
    </form>
</div>
</body>
</html>
