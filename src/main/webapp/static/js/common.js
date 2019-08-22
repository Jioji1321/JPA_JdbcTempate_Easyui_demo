function openWindowById(windowId,url,title,width,height) {
    if(width==""){
        width = 900;
    }
    if(height==""){
        height = 540;
    }
    // alert("openWindowById "+url);
    $(windowId).window({
        width: width,
        height: height,
        top: '100px',
        title: title,
        closable:true,
        modal:true,
        draggable:true
    });
    $(windowId).window('center');
    $(windowId).window('refresh', encodeURI(url)).window('open');
}