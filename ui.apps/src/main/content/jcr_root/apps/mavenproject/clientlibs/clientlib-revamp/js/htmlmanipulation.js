function setBaseComponents() {
    //set body padding-bottom to mobile Docker height
    var mobileDockerHeight = document.querySelector('.docker-sticky-bottom').offsetHeight;
    //set sticky CTA height
    var stickyCTA = document.querySelector('.stickycta-wrapper');
    if(stickyCTA){
        stickyCTA.style.bottom = mobileDockerHeight + 'px';
        var stickyCTAHeight = document.querySelector('.stickycta-wrapper').offsetHeight;
        document.body.style.paddingBottom = mobileDockerHeight + stickyCTAHeight + 'px';
    }
    else{
        document.body.style.paddingBottom = mobileDockerHeight + 'px';
    }
   
}
setBaseComponents();
