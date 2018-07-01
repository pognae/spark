/**
 * @author 
 *   XSized <http://tagg.selfip.com/blog/>
 *   Firejune <to [at] firejune [dot] com>
 *   
 * @license MIT Style
 * 
 * @version 0.9.9b
 *
 * @requires 
 *   - prototype <http://www.prototypejs.org>
 *   - scriptaculous <http://script.aculo.us>
 *   
 * This code may not be used for any purpose except with
 * the written permission of FireJune. MIT-style license.
 *  
 */

// if no debug console
Prototype.consoles = [ "log", "debug", "info", "warn", "error", "dir", "count", "profile", "profileEnd" ];
if ((typeof console != 'undefined' && typeof console.log != 'undefined') && !Prototype.Browser.IE) {
  debug = function(){ console['debug'](arguments); };
  Prototype.consoles.each(function(type){ debug[type] = function(){ console[type](arguments); }; });
} else {
  debug = Prototype.emptyFunction;
  Prototype.consoles.each(function(type){ debug[type] = Prototype.emptyFunction; });
}

/**
 * Returns window width or height and Scroll width or height
 * Core code from - quirksmode.org - Edit for Firefox by pHaez
 * - after modification by Firejune (firejune.com)
 */
Position.getPageSize = function() {
  var xScroll, yScroll, windowWidth, windowHeight;

  if (window.scrollMaxX) {  
    xScroll = window.innerWidth  + window.scrollMaxX;
    yScroll = window.innerHeight + window.scrollMaxY;
  } else {
    xScroll = document.body.scrollWidth;
    yScroll = document.body.scrollHeight;
  }

  if (self.innerHeight) { // all except Explorer
    windowWidth = self.innerWidth;
    windowHeight = self.innerHeight;
  } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
    windowWidth = document.documentElement.clientWidth;
    windowHeight = document.documentElement.clientHeight;
  }

  // for small pages with total height less then height of the viewport
  pageHeight = Math.max(windowHeight, yScroll);
  // for small pages with total width less then width of the viewport
  pageWidth = Math.max(windowWidth, xScroll);

  return { page: { width: pageWidth, height: pageHeight }, window: { width: windowWidth, height: windowHeight } };
};

Position.scrollX = function() {
  return (window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0);
};

Position.scrollY = function() {
  return (window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0);
};

/**
 * ScrollTop effect
 * @author Firejune <to [at] firejune [dot] com>
 * @license MIT
 */ 

Effect.ScrollTop = Class.create();
Object.extend(Object.extend(Effect.ScrollTop.prototype, Effect.Base.prototype), {
  initialize: function(scrollTop) {
    this.scrollTop = scrollTop;
    this.start(arguments[1] || {});
  },
  setup: function() {
    var scrollY = Position.scrollY();
    var getSize = Position.getPageSize();
    var max = getSize.page.height - getSize.window.height;
    if (this.options.offset) scrollY += this.options.offset;
    this.scrollX = Position.scrollX();
    this.scrollStart = scrollY;

    this.delta = (this.scrollTop > max ? max : this.scrollTop) - this.scrollStart;
  },
  update: function(position) {
    window.scrollTo(this.scrollX, this.scrollStart + (position*this.delta));
  }
});

Event.Replayable = Class.create({
  initialize: function(type, target, mouseX, mouseY, scrollY, time, id, href) {
    this.type    = type;
    this.target  = target;
    this.mouseX  = mouseX;
    this.mouseY  = mouseY;
    this.scrollY = scrollY || Position.scrollY();
    this.time    = time;
  	this.id      = id || '';
  	this.href    = href || '';
  }
});

Event.DelayedMoveObserver = Class.create({
  initialize: function(element, delay, callback) {
    this.delay     = delay || 0.5;
    this.element   = $(element);
    this.callback  = callback;
    this.timer     = null;
    this.event     = null;
    this.mouseX    = -1;
    this.mouseY    = -1;
    this.Mousemove_Listener = this.delayedListener.bindAsEventListener(this);
    Event.observe(this.element,'mousemove', this.Mousemove_Listener);
  },
  Mousemove_Listener: null,
  delayedListener: function(event) {
    if (this.timer) clearTimeout(this.timer);
    this.mouseX = Event.pointerX(event);
    this.mouseY = Event.pointerY(event);
    this.target = event.target;
    this.timer  = setTimeout(this.onTimerEvent.bind(this), this.delay * 1000);
  },
  onTimerEvent: function(e) {
    this.timer = null;
    this.callback(new Event.Replayable('mousemove', this.target, this.mouseX, this.mouseY, null, new Date().getTime()) );
  },
  stopObserving: function() {
    Event.stopObserving(this.element,'mousemove', this.Mousemove_Listener);
  }
});

var Tracker = Class.create({
  initialize: function(url) {
    this.url       = url;
    this.events    = new Array();
    this.starttime = new Date().getTime();
    this.mouseObserver = null;
    this.clickObserver = null;
    this.isObserving   = false;

    if (url) this.startObserving();

    debug('Tracker Initialized');
  },
  reset: function() {
    if (this.timer) window.clearTimeout(this.timer);
    this.events    = new Array();
    this.starttime = new Date().getTime();
  },
  addEvent: function(event) {
    event.time = event.time - this.starttime;
    this.events[this.events.length] = event;

    if (event.target && event.target.id)
      event.id = event.target.id;
    if (event.type == 'click' && event.target && event.target.tagName == 'A')
      event.href = event.target.href;
    event.target = (event.target && event.target.tagName) ? event.target.tagName : (event.srcElement ? event.srcElement.tagName : '?') ;      

    return true;
  },
  replayEvents: function() {
    if (!$('crosshair')) {
      new Insertion.Top(document.body, '<div id="crosshair"></div>');
      this.crosshair = $('crosshair');
    }
    this.crosshair.show();
    window.scrollTo(0, 0);
    for (i = 0; i < this.events.length; i++) this.timer = setTimeout(this.replay[this.events[i].type].bind(this, this.events[i]), this.events[i].time);
    this.timer = setTimeout(this.replay.finish.bind(this), this.events[this.events.length - 1].time + 100);
  },
  replay: {
    scroll: function(e) {
      if (this.replay.ScrollTop && this.replay.ScrollTop.state == 'running') this.replay.ScrollTop.cancel();
      this.replay.ScrollTop = new Effect.ScrollTop(e.scrollY, {duration: 0.3});
      if (this.replay.Move && this.replay.Move.state == 'running') this.replay.Move.cancel();
      this.replay.Move = new Effect.Move(this.crosshair, {duration: 0.3, x:e.mouseX, y:e.mouseY, mode: 'absolute'} );
      //debug("SCROLL");
    },
    mousemove: function(e) {
      if (this.replay.Move && this.replay.Move.state == 'running') this.replay.Move.cancel();
      this.replay.Move = new Effect.Move(this.crosshair, {duration: 0.3, x:e.mouseX, y:e.mouseY, mode: 'absolute'} );
      //debug("MOVE");
    },
    click: function(e) {
      this.crosshair.style.backgroundImage = "url(" + window.location.pathname + "images/cur_click.gif)";
      setTimeout(function(){ this.crosshair.style.backgroundImage = "url(" + window.location.pathname + "images/cur.gif)"; }.bind(this), 50);
      if (e.href) {
        document.location = e.href;
        return;
      }
      if (e.id && $(e.id) && e.id != 'upload-filedata') {
        if (document.all) 
          $(e.id).fireEvent("onclick"); 
        else {
          var evt = document.createEvent("MouseEvents");
          evt.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
          var el = $(e.id);
          var canceled = !el.dispatchEvent(evt);
        }
      }
      //Sound.play('select');
      debug.info("CLICK, ID# " + e.id + ", HREF# " + e.href);
    },
    finish: function() {
      this.crosshair.hide();
      this.Playback(this.idx + 1);
    }
  },
  addEvents: function() {
    debug.info(this.json.time + ' URL# ' + this.json.location + ', ID# ' + this.json.id + ', IP# ' + this.json.ip + ', Play Time: ' + this.json.events[this.json.events.length - 1].time / 1000 + 'sec.');
    this.idx = this.json.id;

    var offsetX = (Position.getPageSize().window.width - this.json.window.width) / 2 - 9;
    this.json.events.each(function(e){
      this.addEvent ( new Event.Replayable(e.type, e.target, e.mouseX + offsetX, e.mouseY, e.scrollY, new Date().getTime() + parseInt(e.time), e.id, e.href ) );
    }.bind(this));

    this.replayEvents();
  },
  callEvents: function(url) {
    this.reset();
    new Ajax.Request(url, {
      method:'GET', onSuccess: function(req) {
        setTimeout(function(){
          if (!this.json) {
            setTimeout(function(){
              this.callEvents(url);
            }.bind(this), 50000);
            return;
          }
          var loc = this.json.location;
          document.location = loc + '?replay=' + this.json.id;
        }.bind(this), 100); 
      }.bind(this)
    });
  },
  startObserving: function() {
    this.reset();
    this.mouseObserver = new Event.DelayedMoveObserver (document,  0.04, this.observeMousemove.bind(this) );
    Event.observe(window, 'scroll', this.observeScroll.bindAsEventListener(this));
    Event.observe(document, "click", this.observeClick.bindAsEventListener(this), false);
    Event.observe(window, 'unload', this.sendEvents.bindAsEventListener(this)); // hack
    this.isObserving = true;
  },
  observeScroll: function(event) {
    if (this.scrollTimeout) {
      window.clearTimeout(this.scrollTimeout);
    }
    this.scrollTimeout = window.setTimeout(function() {
      this.addEvent ( new Event.Replayable('scroll', null, this.mouseObserver.mouseX, this.mouseObserver.mouseY, null, new Date().getTime()) );
    }.bind(this), 100);
    return true;
  },
  observeMousemove: function(event) {
    this.addEvent(event);
  },
  Click_Listener: null,
  observeClick: function(event) {
    this.addEvent ( new Event.Replayable('click', event.target ? event.target : (event.srcElement ? event.srcElement : null) , Event.pointerX(event), Event.pointerY(event), null, new Date().getTime()) );
    return true;
  },
  stopObserving: function() {
    this.mouseObserver.stopObserving();
    // Event.stopObserving(document, "click", this.Click_Listener);
    this.isObserving = false;
  },
  sendEvents: function() {
    // Record Play Time Limit
    var playTime = this.events[this.events.length - 1].time; 
    if (!playTime || playTime < 10000 || playTime > 100000) return;
    new Ajax.Request(this.url, {
      method: 'post', asynchronous: false, postBody: Object.toJSON({
        window: Position.getPageSize().window,
        location: document.location.href.split(document.domain)[1].split('#')[0],
        events: this.events
      })
    });
  }
});