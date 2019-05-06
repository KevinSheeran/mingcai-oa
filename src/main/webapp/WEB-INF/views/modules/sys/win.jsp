<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户图像</title>
	<meta name="decorator" content="default"/>
    <link rel="stylesheet" href="${ctxStatic}/windowsstyle/cropper/font-awesome.min.css">
    <link rel="stylesheet" href="${ctxStatic}/windowsstyle/cropper/bootstrap.min.css">
    <link rel="stylesheet" href="${ctxStatic}/windowsstyle/cropper/cropper.css">
    <link rel="stylesheet" href="${ctxStatic}/windowsstyle/cropper/main.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <div class="img-container">
                <img id="image" src="" alt="Picture">
            </div>
        </div>
        <div class="col-md-3">
            <div class="docs-preview clearfix">
                <div class="img-preview preview-lg"></div>
                <div class="img-preview preview-md"></div>
                <div class="img-preview preview-sm"></div>
                <div class="img-preview preview-xs"></div>
            </div>
        </div>
    </div>
    <div class="btn-group btn-group-crop" >
        <button type="button" id="Images" style="display: none;" class="btn btn-success docs-buttons" data-method="getCroppedCanvas" data-option="{ &quot;maxWidth&quot;: 4096, &quot;maxHeight&quot;: 4096 }">
            <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="$().cropper(&quot;getCroppedCanvas&quot;, { maxWidth: 4096, maxHeight: 4096 })">
              Get Cropped Canvas
            </span>
        </button>
            <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
                <input type="file" class="sr-only" id="inputImage" name="file" accept=".jpg,.jpeg,.png,.gif,.bmp,.tiff">
                <span class="docs-tooltip" data-toggle="tooltip" data-animation="false" title="Import image with Blob URLs">
                  <span class="fa fa-upload">选择图片</span>
                </span>
            </label>
    </div>
</div>
<input type="hidden" name="images" value="">
<script src="${ctxStatic}/windowsstyle/cropper/jquery-3.2.1.slim.min.js"></script>
<script src="${ctxStatic}/windowsstyle/cropper/bootstrap.bundle.min.js"></script>
<script src="${ctxStatic}/windowsstyle/cropper/cropper.js"></script>
<script src="${ctxStatic}/windowsstyle/cropper/main.js"></script>
</body>
</html>