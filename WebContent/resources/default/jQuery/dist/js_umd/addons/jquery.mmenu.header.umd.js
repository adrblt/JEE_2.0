(function ( factory ) {
    if ( typeof define === 'function' && define.amd )
    {
        // AMD. Register as an anonymous module.
        define( [ 'jquery' ], factory );
    }
    else if ( typeof exports === 'object' )
    {
        // Node/CommonJS
        factory( require( 'jquery' ) );
    }
    else
    {
        // Browser globals
        factory( jQuery );
    }
}( function ( jQuery ) {


/*	
 * jQuery mmenu header addon
 * mmenu.frebsite.nl
 *
 * Copyright (c) Fred Heusschen
 */
!function(e){var t="mmenu",a="header";e[t].addons[a]={setup:function(){var i=this.opts[a];if(this.conf[a],s=e[t].glbl,"boolean"==typeof i&&(i={add:i,update:i}),"object"!=typeof i&&(i={}),"undefined"==typeof i.content&&(i.content=["prev","title","next"]),i=this.opts[a]=e.extend(!0,{},e[t].defaults[a],i),i.add){if(i.content instanceof Array){for(var d=e("<div />"),h=0,l=i.content.length;l>h;h++)switch(i.content[h]){case"prev":case"next":case"close":d.append('<a class="'+n[i.content[h]]+" "+n.btn+'" href="#"></a>');break;case"title":d.append('<a class="'+n.title+'"></a>');break;default:d.append(i.content[h])}d=d.html()}else var d=i.content;e('<div class="'+n.header+'" />').prependTo(this.$menu).append(d),this.$menu.addClass(n.hasheader),this.bind("init",function(e){e.removeClass(n.hasheader)})}if(this.$header=this.$menu.children("."+n.header),i.update&&this.$header&&this.$header.length){var c=this.$header.find("."+n.title),o=this.$header.find("."+n.prev),f=this.$header.find("."+n.next),p=this.$header.find("."+n.close),u=function(e){e=e||this.$menu.children("."+n.current);var t=e.find("."+this.conf.classNames[a].panelHeader),s=e.find("."+this.conf.classNames[a].panelPrev),d=e.find("."+this.conf.classNames[a].panelNext),h=e.data(r.parent),l=t.html(),p=s.attr("href"),u=d.attr("href"),v=!1,m=s.html(),$=d.html();switch(l||(l=e.children("."+n.header).children("."+n.title).html()),l||(l=i.title),p||(p=e.children("."+n.header).children("."+n.prev).attr("href")),i.titleLink){case"anchor":var v=h?h.children("a").not("."+n.next).attr("href"):!1;break;case"panel":var v=p}c[v?"attr":"removeAttr"]("href",v),c[l?"removeClass":"addClass"](n.hidden),c.html(l),o[p?"attr":"removeAttr"]("href",p),o[p||m?"removeClass":"addClass"](n.hidden),o.html(m),f[u?"attr":"removeAttr"]("href",u),f[u||$?"removeClass":"addClass"](n.hidden),f.html($)};if(this.bind("openPanel",u),this.bind("init",function(){u.call(this,this.$menu.children("."+n.current))}),this.opts.offCanvas){var v=function(e){p.attr("href","#"+e.attr("id"))};v.call(this,s.$page),this.bind("setPage",v)}}},add:function(){n=e[t]._c,r=e[t]._d,i=e[t]._e,n.add("hasheader close")},clickAnchor:function(){}},e[t].defaults[a]={add:!1,title:"Menu",titleLink:"panel",update:!1},e[t].configuration.classNames[a]={panelHeader:"Header",panelNext:"Next",panelPrev:"Prev"};var n,r,i,s}(jQuery);
}));