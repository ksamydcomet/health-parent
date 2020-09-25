// enquire.js v2.0.2 - Awesome Media Queries in JavaScript
// Copyright (c) 2013 Nick Williams - http://wicky.nillia.ms/enquire.js
// License: MIT (http://www.opensource.org/licenses/mit-license.php)
/*! matchMedia() polyfill - Test a CSS media type/query in JS. Authors & copyright (c) 2012: Scott Jehl, Paul Irish, Nicholas Zakas, David Knight. 
 * Dual MIT/BSD license */

window.matchMedia || (window.matchMedia = function () {
    var c = window.d || window.media;
    if (!c) {
        var a = document.createElement("style"),
                k = document.getElementsByTagName("script")[0],
                h = null;
        a.type = "text/css";
        a.id = "matchmediajs-test";
        k.parentNode.insertBefore(a, k);
        h = "getComputedStyle" in window && window.getComputedStyle(a, null) || a.currentStyle;
        c = {
            b: function (b) {
                b = "@media " + b + "{ #matchmediajs-test { width: 1px; } }";
                a.styleSheet ? a.styleSheet.cssText = b : a.textContent = b;
                return "1px" === h.width
            }
        }
    }
    return function (a) {
        return {
            matches: c.b(a ||
                    "all"),
            media: a || "all"
        }
    }
}());

(function () {
    function c() {
        clearTimeout(b);
        b = setTimeout(function () {
            for (var b = 0, f = l.length; b < f; b++) {
                var e = l[b].c,
                        c = l[b].a || [],
                        g = a(e.media).matches;
                if (g !== e.matches) {
                    e.matches = g;
                    for (var g = 0, n = c.length; g < n; g++)
                        c[g].call(window, e)
                }
            }
        }, 30)
    }
    if (window.matchMedia && window.matchMedia("all").addListener)
        return !1;
    var a = window.matchMedia,
            k = a("only all").matches,
            h = !1,
            b = 0,
            l = [];
    window.matchMedia = function (b) {
        var f = a(b),
                e = [],
                m = 0;
        f.addListener = function (a) {
            k && (h || (h = !0, window.addEventListener("resize", c, !0)), 0 === m && (m =
                    l.push({
                        c: f,
                        a: e
                    })), e.push(a))
        };
        f.removeListener = function (a) {
            for (var b = 0, c = e.length; b < c; b++)
                e[b] === a && e.splice(b, 1)
        };
        return f
    }
})();


(function (t) {
    "use strict";

    function i(t, i) {
        var s, n = 0,
                e = t.length;
        for (n; e > n && (s = i(t[n], n), s !== !1); n++)
            ;
    }

    function s(t) {
        return "[object Array]" === Object.prototype.toString.apply(t)
    }

    function n(t) {
        return "function" == typeof t
    }

    function e(t) {
        this.options = t, !t.deferSetup && this.setup()
    }

    function o(t, i) {
        this.query = t, this.isUnconditional = i, this.handlers = [], this.mql = h(t);
        var s = this;
        this.listener = function (t) {
            s.mql = t, s.assess()
        }, this.mql.addListener(this.listener)
    }

    function r() {
        if (!h)
            throw Error("matchMedia not present, legacy browsers require a polyfill");
        this.queries = {}, this.browserIsIncapable = !h("only all").matches
    }
    var h = t.matchMedia;
    e.prototype = {
        setup: function () {
            this.options.setup && this.options.setup(), this.initialised = !0
        },
        on: function () {
            !this.initialised && this.setup(), this.options.match && this.options.match()
        },
        off: function () {
            this.options.unmatch && this.options.unmatch()
        },
        destroy: function () {
            this.options.destroy ? this.options.destroy() : this.off()
        },
        equals: function (t) {
            return this.options === t || this.options.match === t
        }
    }, o.prototype = {
        addHandler: function (t) {
            var i = new e(t);
            this.handlers.push(i), this.matches() && i.on()
        },
        removeHandler: function (t) {
            var s = this.handlers;
            i(s, function (i, n) {
                return i.equals(t) ? (i.destroy(), !s.splice(n, 1)) : void 0
            })
        },
        matches: function () {
            return this.mql.matches || this.isUnconditional
        },
        clear: function () {
            i(this.handlers, function (t) {
                t.destroy()
            }), this.mql.removeListener(this.listener), this.handlers.length = 0
        },
        assess: function () {
            var t = this.matches() ? "on" : "off";
            i(this.handlers, function (i) {
                i[t]()
            })
        }
    }, r.prototype = {
        register: function (t, e, r) {
            var h = this.queries,
                    a = r && this.browserIsIncapable;
            return h[t] || (h[t] = new o(t, a)), n(e) && (e = {
                match: e
            }), s(e) || (e = [e]), i(e, function (i) {
                h[t].addHandler(i)
            }), this
        },
        unregister: function (t, i) {
            var s = this.queries[t];
            return s && (i ? s.removeHandler(i) : (s.clear(), delete this.queries[t])), this
        }
    }, t.enquire = t.enquire || new r
})(this);