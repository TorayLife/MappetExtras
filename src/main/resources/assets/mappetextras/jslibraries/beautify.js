/*
    Beautifier from https://github.com/beautify-web/js-beautify
    Object.assign polyfill from https://github.com/rubennorte/es6-object-assign/tree/master
 */


!function () {
    !function () {
        "use strict";
        var n = [function (t, e, i) {
            var n = i(1).Beautifier, u = i(5).Options;
            t.exports = function (t, e) {
                return new n(t, e).beautify()
            }, t.exports.defaultOptions = function () {
                return new u
            }
        }, function (t, e, i) {
            var n = i(2).Output, u = i(3).Token, o = i(4), _ = i(5).Options, s = i(7).Tokenizer, r = i(7).line_starters,
                h = i(7).positionable_operators, p = i(7).TOKEN;

            function l(t, e) {
                return -1 !== e.indexOf(t)
            }

            function a(t, e) {
                return t && t.type === p.RESERVED && t.text === e
            }

            function f(t, e) {
                return t && t.type === p.RESERVED && l(t.text, e)
            }

            var c = ["case", "return", "do", "if", "throw", "else", "await", "break", "continue", "async"],
                d = function (t) {
                    for (var e = {}, i = 0; i < t.length; i++) e[t[i].replace(/-/g, "_")] = t[i];
                    return e
                }(["before-newline", "after-newline", "preserve-newline"]), b = [d.before_newline, d.preserve_newline],
                g = "BlockStatement", k = "Statement", m = "ObjectLiteral", w = "ArrayLiteral", y = "ForInitializer",
                x = "Conditional", v = "Expression";

            function E(t, e) {
                e.multiline_frame || e.mode === y || e.mode === x || t.remove_indent(e.start_line_index)
            }

            function R(t) {
                return t === w
            }

            function O(t) {
                return l(t, [v, y, x])
            }

            function T(t, e) {
                e = e || {}, this._source_text = t || "", this._output = null, this._tokens = null, this._last_last_text = null, this._flags = null, this._previous_flags = null, this._flag_store = null, this._options = new _(e)
            }

            T.prototype.create_flags = function (t, e) {
                var i = 0;
                return t && (i = t.indentation_level, !this._output.just_added_newline()) && t.line_indent_level > i && (i = t.line_indent_level), {
                    mode: e,
                    parent: t,
                    last_token: t ? t.last_token : new u(p.START_BLOCK, ""),
                    last_word: t ? t.last_word : "",
                    declaration_statement: !1,
                    declaration_assignment: !1,
                    multiline_frame: !1,
                    inline_frame: !1,
                    if_block: !1,
                    else_block: !1,
                    class_start_block: !1,
                    do_block: !1,
                    do_while: !1,
                    import_block: !1,
                    in_case_statement: !1,
                    in_case: !1,
                    case_body: !1,
                    case_block: !1,
                    indentation_level: i,
                    alignment: 0,
                    line_indent_level: t ? t.line_indent_level : i,
                    start_line_index: this._output.get_line_number(),
                    ternary_depth: 0
                }
            }, T.prototype._reset = function (t) {
                var e = t.match(/^[\t ]*/)[0],
                    e = (this._last_last_text = "", this._output = new n(this._options, e), this._output.raw = this._options.test_output_raw, this._flag_store = [], this.set_mode(g), new s(t, this._options));
                return this._tokens = e.tokenize(), t
            }, T.prototype.beautify = function () {
                if (this._options.disabled) return this._source_text;
                for (var t = this._reset(this._source_text), e = this._options.eol, i = ("auto" === this._options.eol && (e = "\n", t) && o.lineBreak.test(t || "") && (e = t.match(o.lineBreak)[0]), this._tokens.next()); i;) this.handle_token(i), this._last_last_text = this._flags.last_token.text, this._flags.last_token = i, i = this._tokens.next();
                return this._output.get_code(e)
            }, T.prototype.handle_token = function (t, e) {
                t.type === p.START_EXPR ? this.handle_start_expr(t) : t.type === p.END_EXPR ? this.handle_end_expr(t) : t.type === p.START_BLOCK ? this.handle_start_block(t) : t.type === p.END_BLOCK ? this.handle_end_block(t) : t.type === p.WORD || t.type === p.RESERVED ? this.handle_word(t) : t.type === p.SEMICOLON ? this.handle_semicolon(t) : t.type === p.STRING ? this.handle_string(t) : t.type === p.EQUALS ? this.handle_equals(t) : t.type === p.OPERATOR ? this.handle_operator(t) : t.type === p.COMMA ? this.handle_comma(t) : t.type === p.BLOCK_COMMENT ? this.handle_block_comment(t, e) : t.type === p.COMMENT ? this.handle_comment(t, e) : t.type === p.DOT ? this.handle_dot(t) : t.type === p.EOF ? this.handle_eof(t) : (t.type, p.UNKNOWN, this.handle_unknown(t, e))
            }, T.prototype.handle_whitespace_and_comments = function (t, e) {
                var i = t.newlines, n = this._options.keep_array_indentation && R(this._flags.mode);
                if (t.comments_before) for (var u = t.comments_before.next(); u;) this.handle_whitespace_and_comments(u, e), this.handle_token(u, e), u = t.comments_before.next();
                if (n) for (var _ = 0; _ < i; _ += 1) this.print_newline(0 < _, e); else if (this._options.max_preserve_newlines && i > this._options.max_preserve_newlines && (i = this._options.max_preserve_newlines), this._options.preserve_newlines && 1 < i) {
                    this.print_newline(!1, e);
                    for (var s = 1; s < i; s += 1) this.print_newline(!0, e)
                }
            };
            var A = ["async", "break", "continue", "return", "throw", "yield"];
            T.prototype.allow_wrap_or_preserved_newline = function (t, e) {
                e = void 0 !== e && e, this._output.just_added_newline() || (e = this._options.preserve_newlines && t.newlines || e, (l(this._flags.last_token.text, h) || l(t.text, h)) && (t = l(this._flags.last_token.text, h) && l(this._options.operator_position, b) || l(t.text, h), e = e && t), e ? this.print_newline(!1, !0) : this._options.wrap_line_length && !f(this._flags.last_token, A) && this._output.set_wrap_point())
            }, T.prototype.print_newline = function (t, e) {
                if (!e && ";" !== this._flags.last_token.text && "," !== this._flags.last_token.text && "=" !== this._flags.last_token.text && (this._flags.last_token.type !== p.OPERATOR || "--" === this._flags.last_token.text || "++" === this._flags.last_token.text)) for (var i = this._tokens.peek(); !(this._flags.mode !== k || this._flags.if_block && a(i, "else") || this._flags.do_block);) this.restore_mode();
                this._output.add_new_line(t) && (this._flags.multiline_frame = !0)
            }, T.prototype.print_token_line_indentation = function (t) {
                this._output.just_added_newline() && (this._options.keep_array_indentation && t.newlines && ("[" === t.text || R(this._flags.mode)) ? (this._output.current_line.set_indent(-1), this._output.current_line.push(t.whitespace_before), this._output.space_before_token = !1) : this._output.set_indent(this._flags.indentation_level, this._flags.alignment) && (this._flags.line_indent_level = this._flags.indentation_level))
            }, T.prototype.print_token = function (t) {
                var e;
                this._output.raw ? this._output.add_raw_token(t) : (this._options.comma_first && t.previous && t.previous.type === p.COMMA && this._output.just_added_newline() && "," === this._output.previous_line.last() && (e = this._output.previous_line.pop(), this._output.previous_line.is_empty() && (this._output.previous_line.push(e), this._output.trim(!0), this._output.current_line.pop(), this._output.trim()), this.print_token_line_indentation(t), this._output.add_token(","), this._output.space_before_token = !0), this.print_token_line_indentation(t), this._output.non_breaking_space = !0, this._output.add_token(t.text), this._output.previous_token_wrapped && (this._flags.multiline_frame = !0))
            }, T.prototype.indent = function () {
                this._flags.indentation_level += 1, this._output.set_indent(this._flags.indentation_level, this._flags.alignment)
            }, T.prototype.deindent = function () {
                0 < this._flags.indentation_level && (!this._flags.parent || this._flags.indentation_level > this._flags.parent.indentation_level) && (--this._flags.indentation_level, this._output.set_indent(this._flags.indentation_level, this._flags.alignment))
            }, T.prototype.set_mode = function (t) {
                this._flags ? (this._flag_store.push(this._flags), this._previous_flags = this._flags) : this._previous_flags = this.create_flags(null, t), this._flags = this.create_flags(this._previous_flags, t), this._output.set_indent(this._flags.indentation_level, this._flags.alignment)
            }, T.prototype.restore_mode = function () {
                0 < this._flag_store.length && (this._previous_flags = this._flags, this._flags = this._flag_store.pop(), this._previous_flags.mode === k && E(this._output, this._previous_flags), this._output.set_indent(this._flags.indentation_level, this._flags.alignment))
            }, T.prototype.start_of_object_property = function () {
                return this._flags.parent.mode === m && this._flags.mode === k && (":" === this._flags.last_token.text && 0 === this._flags.ternary_depth || f(this._flags.last_token, ["get", "set"]))
            }, T.prototype.start_of_statement = function (t) {
                return !!(f(this._flags.last_token, ["var", "let", "const"]) && t.type === p.WORD || a(this._flags.last_token, "do") || !(this._flags.parent.mode === m && this._flags.mode === k) && f(this._flags.last_token, A) && !t.newlines || a(this._flags.last_token, "else") && !(a(t, "if") && !t.comments_before) || this._flags.last_token.type === p.END_EXPR && (this._previous_flags.mode === y || this._previous_flags.mode === x) || this._flags.last_token.type === p.WORD && this._flags.mode === g && !this._flags.in_case && !("--" === t.text || "++" === t.text) && "function" !== this._last_last_text && t.type !== p.WORD && t.type !== p.RESERVED || this._flags.mode === m && (":" === this._flags.last_token.text && 0 === this._flags.ternary_depth || f(this._flags.last_token, ["get", "set"]))) && (this.set_mode(k), this.indent(), this.handle_whitespace_and_comments(t, !0), this.start_of_object_property() || this.allow_wrap_or_preserved_newline(t, f(t, ["do", "for", "if", "while"])), !0)
            }, T.prototype.handle_start_expr = function (t) {
                this.start_of_statement(t) || this.handle_whitespace_and_comments(t);
                var e, i, n = v;
                if ("[" === t.text) {
                    if (this._flags.last_token.type === p.WORD || ")" === this._flags.last_token.text) return f(this._flags.last_token, r) && (this._output.space_before_token = !0), this.print_token(t), this.set_mode(n), this.indent(), void (this._options.space_in_paren && (this._output.space_before_token = !0));
                    n = w, !R(this._flags.mode) || "[" !== this._flags.last_token.text && ("," !== this._flags.last_token.text || "]" !== this._last_last_text && "}" !== this._last_last_text) || this._options.keep_array_indentation || this.print_newline(), l(this._flags.last_token.type, [p.START_EXPR, p.END_EXPR, p.WORD, p.OPERATOR, p.DOT]) || (this._output.space_before_token = !0)
                } else this._flags.last_token.type === p.RESERVED ? "for" === this._flags.last_token.text ? (this._output.space_before_token = this._options.space_before_conditional, n = y) : l(this._flags.last_token.text, ["if", "while", "switch"]) ? (this._output.space_before_token = this._options.space_before_conditional, n = x) : l(this._flags.last_word, ["await", "async"]) ? this._output.space_before_token = !0 : "import" === this._flags.last_token.text && "" === t.whitespace_before ? this._output.space_before_token = !1 : !l(this._flags.last_token.text, r) && "catch" !== this._flags.last_token.text || (this._output.space_before_token = !0) : this._flags.last_token.type === p.EQUALS || this._flags.last_token.type === p.OPERATOR ? this.start_of_object_property() || this.allow_wrap_or_preserved_newline(t) : this._flags.last_token.type === p.WORD ? (this._output.space_before_token = !1, e = this._tokens.peek(-3), this._options.space_after_named_function && e && (i = this._tokens.peek(-4), f(e, ["async", "function"]) || "*" === e.text && f(i, ["async", "function"]) ? this._output.space_before_token = !0 : this._flags.mode === m ? "{" !== e.text && "," !== e.text && ("*" !== e.text || "{" !== i.text && "," !== i.text) || (this._output.space_before_token = !0) : this._flags.parent && this._flags.parent.class_start_block && (this._output.space_before_token = !0))) : this.allow_wrap_or_preserved_newline(t), (this._flags.last_token.type === p.RESERVED && ("function" === this._flags.last_word || "typeof" === this._flags.last_word) || "*" === this._flags.last_token.text && (l(this._last_last_text, ["function", "yield"]) || this._flags.mode === m && l(this._last_last_text, ["{", ","]))) && (this._output.space_before_token = this._options.space_after_anon_function);
                ";" === this._flags.last_token.text || this._flags.last_token.type === p.START_BLOCK ? this.print_newline() : this._flags.last_token.type !== p.END_EXPR && this._flags.last_token.type !== p.START_EXPR && this._flags.last_token.type !== p.END_BLOCK && "." !== this._flags.last_token.text && this._flags.last_token.type !== p.COMMA || this.allow_wrap_or_preserved_newline(t, t.newlines), this.print_token(t), this.set_mode(n), this._options.space_in_paren && (this._output.space_before_token = !0), this.indent()
            }, T.prototype.handle_end_expr = function (t) {
                for (; this._flags.mode === k;) this.restore_mode();
                this.handle_whitespace_and_comments(t), this._flags.multiline_frame && this.allow_wrap_or_preserved_newline(t, "]" === t.text && R(this._flags.mode) && !this._options.keep_array_indentation), this._options.space_in_paren && (this._flags.last_token.type !== p.START_EXPR || this._options.space_in_empty_paren ? this._output.space_before_token = !0 : (this._output.trim(), this._output.space_before_token = !1)), this.deindent(), this.print_token(t), this.restore_mode(), E(this._output, this._previous_flags), this._flags.do_while && this._previous_flags.mode === x && (this._previous_flags.mode = v, this._flags.do_block = !1, this._flags.do_while = !1)
            }, T.prototype.handle_start_block = function (t) {
                this.handle_whitespace_and_comments(t);
                var e = this._tokens.peek(), i = this._tokens.peek(1),
                    i = ("switch" === this._flags.last_word && this._flags.last_token.type === p.END_EXPR ? (this.set_mode(g), this._flags.in_case_statement = !0) : this._flags.case_body ? this.set_mode(g) : i && (l(i.text, [":", ","]) && l(e.type, [p.STRING, p.WORD, p.RESERVED]) || l(e.text, ["get", "set", "..."]) && l(i.type, [p.WORD, p.RESERVED])) ? l(this._last_last_text, ["class", "interface"]) && !l(i.text, [":", ","]) ? this.set_mode(g) : this.set_mode(m) : (this._flags.last_token.type !== p.OPERATOR || "=>" !== this._flags.last_token.text) && (l(this._flags.last_token.type, [p.EQUALS, p.START_EXPR, p.COMMA, p.OPERATOR]) || f(this._flags.last_token, ["return", "throw", "import", "default"])) ? this.set_mode(m) : this.set_mode(g), this._flags.last_token && f(this._flags.last_token.previous, ["class", "extends"]) && (this._flags.class_start_block = !0), !e.comments_before && "}" === e.text),
                    e = i && "function" === this._flags.last_word && this._flags.last_token.type === p.END_EXPR;
                if (this._options.brace_preserve_inline) {
                    var n = 0, u = null;
                    this._flags.inline_frame = !0;
                    do {
                        if ((u = this._tokens.peek((n += 1) - 1)).newlines) {
                            this._flags.inline_frame = !1;
                            break
                        }
                    } while (u.type !== p.EOF && (u.type !== p.END_BLOCK || u.opened !== t))
                }
                ("expand" === this._options.brace_style || "none" === this._options.brace_style && t.newlines) && !this._flags.inline_frame ? this._flags.last_token.type !== p.OPERATOR && (e || this._flags.last_token.type === p.EQUALS || f(this._flags.last_token, c) && "else" !== this._flags.last_token.text) ? this._output.space_before_token = !0 : this.print_newline(!1, !0) : (R(this._previous_flags.mode) && (this._flags.last_token.type === p.START_EXPR || this._flags.last_token.type === p.COMMA) && (this._flags.last_token.type !== p.COMMA && !this._options.space_in_paren || (this._output.space_before_token = !0), this._flags.last_token.type === p.COMMA || this._flags.last_token.type === p.START_EXPR && this._flags.inline_frame) && (this.allow_wrap_or_preserved_newline(t), this._previous_flags.multiline_frame = this._previous_flags.multiline_frame || this._flags.multiline_frame, this._flags.multiline_frame = !1), this._flags.last_token.type !== p.OPERATOR && this._flags.last_token.type !== p.START_EXPR && (l(this._flags.last_token.type, [p.START_BLOCK, p.SEMICOLON]) && !this._flags.inline_frame ? this.print_newline() : this._output.space_before_token = !0)), this.print_token(t), this.indent(), i || this._options.brace_preserve_inline && this._flags.inline_frame || this.print_newline()
            }, T.prototype.handle_end_block = function (t) {
                for (this.handle_whitespace_and_comments(t); this._flags.mode === k;) this.restore_mode();
                var e = this._flags.last_token.type === p.START_BLOCK;
                this._flags.inline_frame && !e ? this._output.space_before_token = !0 : "expand" === this._options.brace_style ? e || this.print_newline() : e || (R(this._flags.mode) && this._options.keep_array_indentation ? (this._options.keep_array_indentation = !1, this.print_newline(), this._options.keep_array_indentation = !0) : this.print_newline()), this.restore_mode(), this.print_token(t)
            }, T.prototype.handle_word = function (t) {
                var e;
                if (t.type === p.RESERVED && (l(t.text, ["set", "get"]) && this._flags.mode !== m || "import" === t.text && l(this._tokens.peek().text, ["(", "."]) || l(t.text, ["as", "from"]) && !this._flags.import_block || this._flags.mode === m && ":" === this._tokens.peek().text) && (t.type = p.WORD), this.start_of_statement(t) ? f(this._flags.last_token, ["var", "let", "const"]) && t.type === p.WORD && (this._flags.declaration_statement = !0) : !t.newlines || O(this._flags.mode) || this._flags.last_token.type === p.OPERATOR && "--" !== this._flags.last_token.text && "++" !== this._flags.last_token.text || this._flags.last_token.type === p.EQUALS || !this._options.preserve_newlines && f(this._flags.last_token, ["var", "let", "const", "set", "get"]) ? this.handle_whitespace_and_comments(t) : (this.handle_whitespace_and_comments(t), this.print_newline()), this._flags.do_block && !this._flags.do_while) {
                    if (a(t, "while")) return this._output.space_before_token = !0, this.print_token(t), this._output.space_before_token = !0, void (this._flags.do_while = !0);
                    this.print_newline(), this._flags.do_block = !1
                }
                if (this._flags.if_block) if (!this._flags.else_block && a(t, "else")) this._flags.else_block = !0; else {
                    for (; this._flags.mode === k;) this.restore_mode();
                    this._flags.if_block = !1, this._flags.else_block = !1
                }
                this._flags.in_case_statement && f(t, ["case", "default"]) ? (this.print_newline(), this._flags.case_block || !this._flags.case_body && !this._options.jslint_happy || this.deindent(), this._flags.case_body = !1, this.print_token(t), this._flags.in_case = !0) : (this._flags.last_token.type !== p.COMMA && this._flags.last_token.type !== p.START_EXPR && this._flags.last_token.type !== p.EQUALS && this._flags.last_token.type !== p.OPERATOR || this.start_of_object_property() || this.allow_wrap_or_preserved_newline(t), a(t, "function") ? (!(l(this._flags.last_token.text, ["}", ";"]) || this._output.just_added_newline() && !l(this._flags.last_token.text, ["(", "[", "{", ":", "=", ","]) && this._flags.last_token.type !== p.OPERATOR) || this._output.just_added_blankline() || t.comments_before || (this.print_newline(), this.print_newline(!0)), this._flags.last_token.type === p.RESERVED || this._flags.last_token.type === p.WORD ? f(this._flags.last_token, ["get", "set", "new", "export"]) || f(this._flags.last_token, A) || a(this._flags.last_token, "default") && "export" === this._last_last_text || "declare" === this._flags.last_token.text ? this._output.space_before_token = !0 : this.print_newline() : this._flags.last_token.type === p.OPERATOR || "=" === this._flags.last_token.text ? this._output.space_before_token = !0 : !this._flags.multiline_frame && (O(this._flags.mode) || R(this._flags.mode)) || this.print_newline(), this.print_token(t), this._flags.last_word = t.text) : (e = "NONE", this._flags.last_token.type === p.END_BLOCK ? this._previous_flags.inline_frame ? e = "SPACE" : !f(t, ["else", "catch", "finally", "from"]) || "expand" === this._options.brace_style || "end-expand" === this._options.brace_style || "none" === this._options.brace_style && t.newlines ? e = "NEWLINE" : (e = "SPACE", this._output.space_before_token = !0) : this._flags.last_token.type === p.SEMICOLON && this._flags.mode === g ? e = "NEWLINE" : this._flags.last_token.type === p.SEMICOLON && O(this._flags.mode) ? e = "SPACE" : this._flags.last_token.type === p.STRING ? e = "NEWLINE" : this._flags.last_token.type === p.RESERVED || this._flags.last_token.type === p.WORD || "*" === this._flags.last_token.text && (l(this._last_last_text, ["function", "yield"]) || this._flags.mode === m && l(this._last_last_text, ["{", ","])) ? e = "SPACE" : this._flags.last_token.type === p.START_BLOCK ? e = this._flags.inline_frame ? "SPACE" : "NEWLINE" : this._flags.last_token.type === p.END_EXPR && (this._output.space_before_token = !0, e = "NEWLINE"), f(t, r) && ")" !== this._flags.last_token.text && (e = this._flags.inline_frame || "else" === this._flags.last_token.text || "export" === this._flags.last_token.text ? "SPACE" : "NEWLINE"), f(t, ["else", "catch", "finally"]) ? (this._flags.last_token.type !== p.END_BLOCK || this._previous_flags.mode !== g || "expand" === this._options.brace_style || "end-expand" === this._options.brace_style || "none" === this._options.brace_style && t.newlines) && !this._flags.inline_frame ? this.print_newline() : (this._output.trim(!0), "}" !== this._output.current_line.last() && this.print_newline(), this._output.space_before_token = !0) : "NEWLINE" === e ? f(this._flags.last_token, c) || "declare" === this._flags.last_token.text && f(t, ["var", "let", "const"]) ? this._output.space_before_token = !0 : this._flags.last_token.type !== p.END_EXPR ? this._flags.last_token.type === p.START_EXPR && f(t, ["var", "let", "const"]) || ":" === this._flags.last_token.text || (a(t, "if") && a(t.previous, "else") ? this._output.space_before_token = !0 : this.print_newline()) : f(t, r) && ")" !== this._flags.last_token.text && this.print_newline() : this._flags.multiline_frame && R(this._flags.mode) && "," === this._flags.last_token.text && "}" === this._last_last_text ? this.print_newline() : "SPACE" === e && (this._output.space_before_token = !0), !t.previous || t.previous.type !== p.WORD && t.previous.type !== p.RESERVED || (this._output.space_before_token = !0), this.print_token(t), this._flags.last_word = t.text, t.type === p.RESERVED && ("do" === t.text ? this._flags.do_block = !0 : "if" === t.text ? this._flags.if_block = !0 : "import" === t.text ? this._flags.import_block = !0 : this._flags.import_block && a(t, "from") && (this._flags.import_block = !1))))
            }, T.prototype.handle_semicolon = function (t) {
                this.start_of_statement(t) ? this._output.space_before_token = !1 : this.handle_whitespace_and_comments(t);
                for (var e = this._tokens.peek(); !(this._flags.mode !== k || this._flags.if_block && a(e, "else") || this._flags.do_block);) this.restore_mode();
                this._flags.import_block && (this._flags.import_block = !1), this.print_token(t)
            }, T.prototype.handle_string = function (t) {
                t.text.startsWith("`") && 0 === t.newlines && "" === t.whitespace_before && (")" === t.previous.text || this._flags.last_token.type === p.WORD) || (this.start_of_statement(t) || (this.handle_whitespace_and_comments(t), this._flags.last_token.type === p.RESERVED) || this._flags.last_token.type === p.WORD || this._flags.inline_frame ? this._output.space_before_token = !0 : this._flags.last_token.type === p.COMMA || this._flags.last_token.type === p.START_EXPR || this._flags.last_token.type === p.EQUALS || this._flags.last_token.type === p.OPERATOR ? this.start_of_object_property() || this.allow_wrap_or_preserved_newline(t) : !t.text.startsWith("`") || this._flags.last_token.type !== p.END_EXPR || "]" !== t.previous.text && ")" !== t.previous.text || 0 !== t.newlines ? this.print_newline() : this._output.space_before_token = !0), this.print_token(t)
            }, T.prototype.handle_equals = function (t) {
                this.start_of_statement(t) || this.handle_whitespace_and_comments(t), this._flags.declaration_statement && (this._flags.declaration_assignment = !0), this._output.space_before_token = !0, this.print_token(t), this._output.space_before_token = !0
            }, T.prototype.handle_comma = function (t) {
                this.handle_whitespace_and_comments(t, !0), this.print_token(t), this._output.space_before_token = !0, this._flags.declaration_statement ? (O(this._flags.parent.mode) && (this._flags.declaration_assignment = !1), this._flags.declaration_assignment ? (this._flags.declaration_assignment = !1, this.print_newline(!1, !0)) : this._options.comma_first && this.allow_wrap_or_preserved_newline(t)) : this._flags.mode === m || this._flags.mode === k && this._flags.parent.mode === m ? (this._flags.mode === k && this.restore_mode(), this._flags.inline_frame || this.print_newline()) : this._options.comma_first && this.allow_wrap_or_preserved_newline(t)
            }, T.prototype.handle_operator = function (t) {
                var e = "*" === t.text && (f(this._flags.last_token, ["function", "yield"]) || l(this._flags.last_token.type, [p.START_BLOCK, p.COMMA, p.END_BLOCK, p.SEMICOLON])),
                    i = l(t.text, ["-", "+"]) && (l(this._flags.last_token.type, [p.START_BLOCK, p.START_EXPR, p.EQUALS, p.OPERATOR]) || l(this._flags.last_token.text, r) || "," === this._flags.last_token.text);
                if (this.start_of_statement(t) || this.handle_whitespace_and_comments(t, !e), "*" === t.text && this._flags.last_token.type === p.DOT) this.print_token(t); else if ("::" === t.text) this.print_token(t); else if (this._flags.last_token.type === p.OPERATOR && l(this._options.operator_position, b) && this.allow_wrap_or_preserved_newline(t), ":" === t.text && this._flags.in_case) this.print_token(t), this._flags.in_case = !1, this._flags.case_body = !0, this._tokens.peek().type !== p.START_BLOCK ? (this.indent(), this.print_newline(), this._flags.case_block = !1) : (this._flags.case_block = !0, this._output.space_before_token = !0); else {
                    var n = !0, u = !0, _ = !1;
                    if (":" === t.text ? 0 === this._flags.ternary_depth ? n = !1 : (--this._flags.ternary_depth, _ = !0) : "?" === t.text && (this._flags.ternary_depth += 1), !i && !e && this._options.preserve_newlines && l(t.text, h)) {
                        var s = ":" === t.text, a = s && _, o = s && !_;
                        switch (this._options.operator_position) {
                            case d.before_newline:
                                return this._output.space_before_token = !o, this.print_token(t), s && !a || this.allow_wrap_or_preserved_newline(t), void (this._output.space_before_token = !0);
                            case d.after_newline:
                                return this._output.space_before_token = !0, !s || a ? this._tokens.peek().newlines ? this.print_newline(!1, !0) : this.allow_wrap_or_preserved_newline(t) : this._output.space_before_token = !1, this.print_token(t), void (this._output.space_before_token = !0);
                            case d.preserve_newline:
                                return o || this.allow_wrap_or_preserved_newline(t), n = !(this._output.just_added_newline() || o), this._output.space_before_token = n, this.print_token(t), void (this._output.space_before_token = !0)
                        }
                    }
                    e ? (this.allow_wrap_or_preserved_newline(t), n = !1, u = (_ = this._tokens.peek()) && l(_.type, [p.WORD, p.RESERVED])) : "..." === t.text ? (this.allow_wrap_or_preserved_newline(t), n = this._flags.last_token.type === p.START_BLOCK, u = !1) : (l(t.text, ["--", "++", "!", "~"]) || i) && (this._flags.last_token.type !== p.COMMA && this._flags.last_token.type !== p.START_EXPR || this.allow_wrap_or_preserved_newline(t), u = n = !1, !t.newlines || "--" !== t.text && "++" !== t.text && "~" !== t.text || ((e = f(this._flags.last_token, c) && t.newlines) && (this._previous_flags.if_block || this._previous_flags.else_block) && this.restore_mode(), this.print_newline(e, !0)), ";" === this._flags.last_token.text && O(this._flags.mode) && (n = !0), this._flags.last_token.type === p.RESERVED ? n = !0 : this._flags.last_token.type === p.END_EXPR ? n = !("]" === this._flags.last_token.text && ("--" === t.text || "++" === t.text)) : this._flags.last_token.type === p.OPERATOR && (n = l(t.text, ["--", "-", "++", "+"]) && l(this._flags.last_token.text, ["--", "-", "++", "+"]), l(t.text, ["+", "-"])) && l(this._flags.last_token.text, ["--", "++"]) && (u = !0), (this._flags.mode !== g || this._flags.inline_frame) && this._flags.mode !== k || "{" !== this._flags.last_token.text && ";" !== this._flags.last_token.text || this.print_newline()), this._output.space_before_token = this._output.space_before_token || n, this.print_token(t), this._output.space_before_token = u
                }
            }, T.prototype.handle_block_comment = function (t, e) {
                this._output.raw ? (this._output.add_raw_token(t), t.directives && "end" === t.directives.preserve && (this._output.raw = this._options.test_output_raw)) : t.directives ? (this.print_newline(!1, e), this.print_token(t), "start" === t.directives.preserve && (this._output.raw = !0), this.print_newline(!1, !0)) : o.newline.test(t.text) || t.newlines ? this.print_block_commment(t, e) : (this._output.space_before_token = !0, this.print_token(t), this._output.space_before_token = !0)
            }, T.prototype.print_block_commment = function (t, e) {
                var i, n, u, _ = function (t) {
                    for (var e = [], i = (t = t.replace(o.allLineBreaks, "\n")).indexOf("\n"); -1 !== i;) e.push(t.substring(0, i)), i = (t = t.substring(i + 1)).indexOf("\n");
                    return t.length && e.push(t), e
                }(t.text), s = t.whitespace_before, a = s.length;
                if (this.print_newline(!1, e), this.print_token_line_indentation(t), this._output.add_token(_[0]), this.print_newline(!1, e), 1 < _.length) {
                    for (n = function (t, e) {
                        for (var i = 0; i < t.length; i++) if (t[i].trim().charAt(0) !== e) return !1;
                        return !0
                    }(_ = _.slice(1), "*"), u = function (t, e) {
                        for (var i, n = 0, u = t.length; n < u; n++) if ((i = t[n]) && 0 !== i.indexOf(e)) return !1;
                        return !0
                    }(_, s), n && (this._flags.alignment = 1), i = 0; i < _.length; i++) n ? (this.print_token_line_indentation(t), this._output.add_token(_[i].replace(/^\s+/g, ""))) : u && _[i] ? (this.print_token_line_indentation(t), this._output.add_token(_[i].substring(a))) : (this._output.current_line.set_indent(-1), this._output.add_token(_[i])), this.print_newline(!1, e);
                    this._flags.alignment = 0
                }
            }, T.prototype.handle_comment = function (t, e) {
                t.newlines ? this.print_newline(!1, e) : this._output.trim(!0), this._output.space_before_token = !0, this.print_token(t), this.print_newline(!1, e)
            }, T.prototype.handle_dot = function (t) {
                this.start_of_statement(t) || this.handle_whitespace_and_comments(t, !0), this._flags.last_token.text.match("^[0-9]+$") && (this._output.space_before_token = !0), f(this._flags.last_token, c) ? this._output.space_before_token = !1 : this.allow_wrap_or_preserved_newline(t, ")" === this._flags.last_token.text && this._options.break_chained_methods), this._options.unindent_chained_methods && this._output.just_added_newline() && this.deindent(), this.print_token(t)
            }, T.prototype.handle_unknown = function (t, e) {
                this.print_token(t), "\n" === t.text[t.text.length - 1] && this.print_newline(!1, e)
            }, T.prototype.handle_eof = function (t) {
                for (; this._flags.mode === k;) this.restore_mode();
                this.handle_whitespace_and_comments(t)
            }, t.exports.Beautifier = T
        }, function (t) {
            function u(t) {
                this.__parent = t, this.__character_count = 0, this.__indent_count = -1, this.__alignment_count = 0, this.__wrap_point_index = 0, this.__wrap_point_character_count = 0, this.__wrap_point_indent_count = -1, this.__wrap_point_alignment_count = 0, this.__items = []
            }

            function i(t, e) {
                this.__cache = [""], this.__indent_size = t.indent_size, this.__indent_string = t.indent_char, t.indent_with_tabs || (this.__indent_string = new Array(t.indent_size + 1).join(t.indent_char)), e = e || "", 0 < t.indent_level && (e = new Array(t.indent_level + 1).join(this.__indent_string)), this.__base_string = e, this.__base_string_length = e.length
            }

            function e(t, e) {
                this.__indent_cache = new i(t, e), this.raw = !1, this._end_with_newline = t.end_with_newline, this.indent_size = t.indent_size, this.wrap_line_length = t.wrap_line_length, this.indent_empty_lines = t.indent_empty_lines, this.__lines = [], this.previous_line = null, this.current_line = null, this.next_line = new u(this), this.space_before_token = !1, this.non_breaking_space = !1, this.previous_token_wrapped = !1, this.__add_outputline()
            }

            u.prototype.clone_empty = function () {
                var t = new u(this.__parent);
                return t.set_indent(this.__indent_count, this.__alignment_count), t
            }, u.prototype.item = function (t) {
                return t < 0 ? this.__items[this.__items.length + t] : this.__items[t]
            }, u.prototype.has_match = function (t) {
                for (var e = this.__items.length - 1; 0 <= e; e--) if (this.__items[e].match(t)) return !0;
                return !1
            }, u.prototype.set_indent = function (t, e) {
                this.is_empty() && (this.__indent_count = t || 0, this.__alignment_count = e || 0, this.__character_count = this.__parent.get_indent_size(this.__indent_count, this.__alignment_count))
            }, u.prototype._set_wrap_point = function () {
                this.__parent.wrap_line_length && (this.__wrap_point_index = this.__items.length, this.__wrap_point_character_count = this.__character_count, this.__wrap_point_indent_count = this.__parent.next_line.__indent_count, this.__wrap_point_alignment_count = this.__parent.next_line.__alignment_count)
            }, u.prototype._should_wrap = function () {
                return this.__wrap_point_index && this.__character_count > this.__parent.wrap_line_length && this.__wrap_point_character_count > this.__parent.next_line.__character_count
            }, u.prototype._allow_wrap = function () {
                var t;
                return !!this._should_wrap() && (this.__parent.add_new_line(), (t = this.__parent.current_line).set_indent(this.__wrap_point_indent_count, this.__wrap_point_alignment_count), t.__items = this.__items.slice(this.__wrap_point_index), this.__items = this.__items.slice(0, this.__wrap_point_index), t.__character_count += this.__character_count - this.__wrap_point_character_count, this.__character_count = this.__wrap_point_character_count, " " === t.__items[0] && (t.__items.splice(0, 1), --t.__character_count), !0)
            }, u.prototype.is_empty = function () {
                return 0 === this.__items.length
            }, u.prototype.last = function () {
                return this.is_empty() ? null : this.__items[this.__items.length - 1]
            }, u.prototype.push = function (t) {
                this.__items.push(t);
                var e = t.lastIndexOf("\n");
                -1 !== e ? this.__character_count = t.length - e : this.__character_count += t.length
            }, u.prototype.pop = function () {
                var t = null;
                return this.is_empty() || (t = this.__items.pop(), this.__character_count -= t.length), t
            }, u.prototype._remove_indent = function () {
                0 < this.__indent_count && (--this.__indent_count, this.__character_count -= this.__parent.indent_size)
            }, u.prototype._remove_wrap_indent = function () {
                0 < this.__wrap_point_indent_count && --this.__wrap_point_indent_count
            }, u.prototype.trim = function () {
                for (; " " === this.last();) this.__items.pop(), --this.__character_count
            }, u.prototype.toString = function () {
                var t = "";
                return this.is_empty() ? this.__parent.indent_empty_lines && (t = this.__parent.get_indent_string(this.__indent_count)) : (t = this.__parent.get_indent_string(this.__indent_count, this.__alignment_count), t += this.__items.join("")), t
            }, i.prototype.get_indent_size = function (t, e) {
                var i = this.__base_string_length;
                return i = (i = t < 0 ? 0 : i) + t * this.__indent_size + (e = e || 0)
            }, i.prototype.get_indent_string = function (t, e) {
                var i = this.__base_string;
                return e = e || 0, t < 0 && (t = 0, i = ""), e += t * this.__indent_size, this.__ensure_cache(e), i += this.__cache[e]
            }, i.prototype.__ensure_cache = function (t) {
                for (; t >= this.__cache.length;) this.__add_column()
            }, i.prototype.__add_column = function () {
                var t, e = this.__cache.length, i = "";
                this.__indent_size && e >= this.__indent_size && (e -= (t = Math.floor(e / this.__indent_size)) * this.__indent_size, i = new Array(t + 1).join(this.__indent_string)), e && (i += new Array(e + 1).join(" ")), this.__cache.push(i)
            }, e.prototype.__add_outputline = function () {
                this.previous_line = this.current_line, this.current_line = this.next_line.clone_empty(), this.__lines.push(this.current_line)
            }, e.prototype.get_line_number = function () {
                return this.__lines.length
            }, e.prototype.get_indent_string = function (t, e) {
                return this.__indent_cache.get_indent_string(t, e)
            }, e.prototype.get_indent_size = function (t, e) {
                return this.__indent_cache.get_indent_size(t, e)
            }, e.prototype.is_empty = function () {
                return !this.previous_line && this.current_line.is_empty()
            }, e.prototype.add_new_line = function (t) {
                return !(this.is_empty() || !t && this.just_added_newline() || (this.raw || this.__add_outputline(), 0))
            }, e.prototype.get_code = function (t) {
                this.trim(!0);
                var e = this.current_line.pop(),
                    e = (e && ("\n" === e[e.length - 1] && (e = e.replace(/\n+$/g, "")), this.current_line.push(e)), this._end_with_newline && this.__add_outputline(), this.__lines.join("\n"));
                return e = "\n" !== t ? e.replace(/[\n]/g, t) : e
            }, e.prototype.set_wrap_point = function () {
                this.current_line._set_wrap_point()
            }, e.prototype.set_indent = function (t, e) {
                return this.next_line.set_indent(t = t || 0, e = e || 0), 1 < this.__lines.length ? (this.current_line.set_indent(t, e), !0) : (this.current_line.set_indent(), !1)
            }, e.prototype.add_raw_token = function (t) {
                for (var e = 0; e < t.newlines; e++) this.__add_outputline();
                this.current_line.set_indent(-1), this.current_line.push(t.whitespace_before), this.current_line.push(t.text), this.space_before_token = !1, this.non_breaking_space = !1, this.previous_token_wrapped = !1
            }, e.prototype.add_token = function (t) {
                this.__add_space_before_token(), this.current_line.push(t), this.space_before_token = !1, this.non_breaking_space = !1, this.previous_token_wrapped = this.current_line._allow_wrap()
            }, e.prototype.__add_space_before_token = function () {
                this.space_before_token && !this.just_added_newline() && (this.non_breaking_space || this.set_wrap_point(), this.current_line.push(" "))
            }, e.prototype.remove_indent = function (t) {
                for (var e = this.__lines.length; t < e;) this.__lines[t]._remove_indent(), t++;
                this.current_line._remove_wrap_indent()
            }, e.prototype.trim = function (t) {
                for (t = void 0 !== t && t, this.current_line.trim(); t && 1 < this.__lines.length && this.current_line.is_empty();) this.__lines.pop(), this.current_line = this.__lines[this.__lines.length - 1], this.current_line.trim();
                this.previous_line = 1 < this.__lines.length ? this.__lines[this.__lines.length - 2] : null
            }, e.prototype.just_added_newline = function () {
                return this.current_line.is_empty()
            }, e.prototype.just_added_blankline = function () {
                return this.is_empty() || this.current_line.is_empty() && this.previous_line.is_empty()
            }, e.prototype.ensure_empty_line_above = function (t, e) {
                for (var i = this.__lines.length - 2; 0 <= i;) {
                    var n = this.__lines[i];
                    if (n.is_empty()) break;
                    if (0 !== n.item(0).indexOf(t) && n.item(-1) !== e) {
                        this.__lines.splice(i + 1, 0, new u(this)), this.previous_line = this.__lines[this.__lines.length - 2];
                        break
                    }
                    i--
                }
            }, t.exports.Output = e
        }, function (t) {
            t.exports.Token = function (t, e, i, n) {
                this.type = t, this.text = e, this.comments_before = null, this.newlines = i || 0, this.whitespace_before = n || "", this.parent = null, this.next = null, this.previous = null, this.opened = null, this.closed = null, this.directives = null
            }
        }, function (t, e) {
            var i = "\\x24\\x30-\\x39\\x41-\\x5a\\x5f\\x61-\\x7a",
                n = "\\xaa\\xb5\\xba\\xc0-\\xd6\\xd8-\\xf6\\xf8-\\u02c1\\u02c6-\\u02d1\\u02e0-\\u02e4\\u02ec\\u02ee\\u0370-\\u0374\\u0376\\u0377\\u037a-\\u037d\\u0386\\u0388-\\u038a\\u038c\\u038e-\\u03a1\\u03a3-\\u03f5\\u03f7-\\u0481\\u048a-\\u0527\\u0531-\\u0556\\u0559\\u0561-\\u0587\\u05d0-\\u05ea\\u05f0-\\u05f2\\u0620-\\u064a\\u066e\\u066f\\u0671-\\u06d3\\u06d5\\u06e5\\u06e6\\u06ee\\u06ef\\u06fa-\\u06fc\\u06ff\\u0710\\u0712-\\u072f\\u074d-\\u07a5\\u07b1\\u07ca-\\u07ea\\u07f4\\u07f5\\u07fa\\u0800-\\u0815\\u081a\\u0824\\u0828\\u0840-\\u0858\\u08a0\\u08a2-\\u08ac\\u0904-\\u0939\\u093d\\u0950\\u0958-\\u0961\\u0971-\\u0977\\u0979-\\u097f\\u0985-\\u098c\\u098f\\u0990\\u0993-\\u09a8\\u09aa-\\u09b0\\u09b2\\u09b6-\\u09b9\\u09bd\\u09ce\\u09dc\\u09dd\\u09df-\\u09e1\\u09f0\\u09f1\\u0a05-\\u0a0a\\u0a0f\\u0a10\\u0a13-\\u0a28\\u0a2a-\\u0a30\\u0a32\\u0a33\\u0a35\\u0a36\\u0a38\\u0a39\\u0a59-\\u0a5c\\u0a5e\\u0a72-\\u0a74\\u0a85-\\u0a8d\\u0a8f-\\u0a91\\u0a93-\\u0aa8\\u0aaa-\\u0ab0\\u0ab2\\u0ab3\\u0ab5-\\u0ab9\\u0abd\\u0ad0\\u0ae0\\u0ae1\\u0b05-\\u0b0c\\u0b0f\\u0b10\\u0b13-\\u0b28\\u0b2a-\\u0b30\\u0b32\\u0b33\\u0b35-\\u0b39\\u0b3d\\u0b5c\\u0b5d\\u0b5f-\\u0b61\\u0b71\\u0b83\\u0b85-\\u0b8a\\u0b8e-\\u0b90\\u0b92-\\u0b95\\u0b99\\u0b9a\\u0b9c\\u0b9e\\u0b9f\\u0ba3\\u0ba4\\u0ba8-\\u0baa\\u0bae-\\u0bb9\\u0bd0\\u0c05-\\u0c0c\\u0c0e-\\u0c10\\u0c12-\\u0c28\\u0c2a-\\u0c33\\u0c35-\\u0c39\\u0c3d\\u0c58\\u0c59\\u0c60\\u0c61\\u0c85-\\u0c8c\\u0c8e-\\u0c90\\u0c92-\\u0ca8\\u0caa-\\u0cb3\\u0cb5-\\u0cb9\\u0cbd\\u0cde\\u0ce0\\u0ce1\\u0cf1\\u0cf2\\u0d05-\\u0d0c\\u0d0e-\\u0d10\\u0d12-\\u0d3a\\u0d3d\\u0d4e\\u0d60\\u0d61\\u0d7a-\\u0d7f\\u0d85-\\u0d96\\u0d9a-\\u0db1\\u0db3-\\u0dbb\\u0dbd\\u0dc0-\\u0dc6\\u0e01-\\u0e30\\u0e32\\u0e33\\u0e40-\\u0e46\\u0e81\\u0e82\\u0e84\\u0e87\\u0e88\\u0e8a\\u0e8d\\u0e94-\\u0e97\\u0e99-\\u0e9f\\u0ea1-\\u0ea3\\u0ea5\\u0ea7\\u0eaa\\u0eab\\u0ead-\\u0eb0\\u0eb2\\u0eb3\\u0ebd\\u0ec0-\\u0ec4\\u0ec6\\u0edc-\\u0edf\\u0f00\\u0f40-\\u0f47\\u0f49-\\u0f6c\\u0f88-\\u0f8c\\u1000-\\u102a\\u103f\\u1050-\\u1055\\u105a-\\u105d\\u1061\\u1065\\u1066\\u106e-\\u1070\\u1075-\\u1081\\u108e\\u10a0-\\u10c5\\u10c7\\u10cd\\u10d0-\\u10fa\\u10fc-\\u1248\\u124a-\\u124d\\u1250-\\u1256\\u1258\\u125a-\\u125d\\u1260-\\u1288\\u128a-\\u128d\\u1290-\\u12b0\\u12b2-\\u12b5\\u12b8-\\u12be\\u12c0\\u12c2-\\u12c5\\u12c8-\\u12d6\\u12d8-\\u1310\\u1312-\\u1315\\u1318-\\u135a\\u1380-\\u138f\\u13a0-\\u13f4\\u1401-\\u166c\\u166f-\\u167f\\u1681-\\u169a\\u16a0-\\u16ea\\u16ee-\\u16f0\\u1700-\\u170c\\u170e-\\u1711\\u1720-\\u1731\\u1740-\\u1751\\u1760-\\u176c\\u176e-\\u1770\\u1780-\\u17b3\\u17d7\\u17dc\\u1820-\\u1877\\u1880-\\u18a8\\u18aa\\u18b0-\\u18f5\\u1900-\\u191c\\u1950-\\u196d\\u1970-\\u1974\\u1980-\\u19ab\\u19c1-\\u19c7\\u1a00-\\u1a16\\u1a20-\\u1a54\\u1aa7\\u1b05-\\u1b33\\u1b45-\\u1b4b\\u1b83-\\u1ba0\\u1bae\\u1baf\\u1bba-\\u1be5\\u1c00-\\u1c23\\u1c4d-\\u1c4f\\u1c5a-\\u1c7d\\u1ce9-\\u1cec\\u1cee-\\u1cf1\\u1cf5\\u1cf6\\u1d00-\\u1dbf\\u1e00-\\u1f15\\u1f18-\\u1f1d\\u1f20-\\u1f45\\u1f48-\\u1f4d\\u1f50-\\u1f57\\u1f59\\u1f5b\\u1f5d\\u1f5f-\\u1f7d\\u1f80-\\u1fb4\\u1fb6-\\u1fbc\\u1fbe\\u1fc2-\\u1fc4\\u1fc6-\\u1fcc\\u1fd0-\\u1fd3\\u1fd6-\\u1fdb\\u1fe0-\\u1fec\\u1ff2-\\u1ff4\\u1ff6-\\u1ffc\\u2071\\u207f\\u2090-\\u209c\\u2102\\u2107\\u210a-\\u2113\\u2115\\u2119-\\u211d\\u2124\\u2126\\u2128\\u212a-\\u212d\\u212f-\\u2139\\u213c-\\u213f\\u2145-\\u2149\\u214e\\u2160-\\u2188\\u2c00-\\u2c2e\\u2c30-\\u2c5e\\u2c60-\\u2ce4\\u2ceb-\\u2cee\\u2cf2\\u2cf3\\u2d00-\\u2d25\\u2d27\\u2d2d\\u2d30-\\u2d67\\u2d6f\\u2d80-\\u2d96\\u2da0-\\u2da6\\u2da8-\\u2dae\\u2db0-\\u2db6\\u2db8-\\u2dbe\\u2dc0-\\u2dc6\\u2dc8-\\u2dce\\u2dd0-\\u2dd6\\u2dd8-\\u2dde\\u2e2f\\u3005-\\u3007\\u3021-\\u3029\\u3031-\\u3035\\u3038-\\u303c\\u3041-\\u3096\\u309d-\\u309f\\u30a1-\\u30fa\\u30fc-\\u30ff\\u3105-\\u312d\\u3131-\\u318e\\u31a0-\\u31ba\\u31f0-\\u31ff\\u3400-\\u4db5\\u4e00-\\u9fcc\\ua000-\\ua48c\\ua4d0-\\ua4fd\\ua500-\\ua60c\\ua610-\\ua61f\\ua62a\\ua62b\\ua640-\\ua66e\\ua67f-\\ua697\\ua6a0-\\ua6ef\\ua717-\\ua71f\\ua722-\\ua788\\ua78b-\\ua78e\\ua790-\\ua793\\ua7a0-\\ua7aa\\ua7f8-\\ua801\\ua803-\\ua805\\ua807-\\ua80a\\ua80c-\\ua822\\ua840-\\ua873\\ua882-\\ua8b3\\ua8f2-\\ua8f7\\ua8fb\\ua90a-\\ua925\\ua930-\\ua946\\ua960-\\ua97c\\ua984-\\ua9b2\\ua9cf\\uaa00-\\uaa28\\uaa40-\\uaa42\\uaa44-\\uaa4b\\uaa60-\\uaa76\\uaa7a\\uaa80-\\uaaaf\\uaab1\\uaab5\\uaab6\\uaab9-\\uaabd\\uaac0\\uaac2\\uaadb-\\uaadd\\uaae0-\\uaaea\\uaaf2-\\uaaf4\\uab01-\\uab06\\uab09-\\uab0e\\uab11-\\uab16\\uab20-\\uab26\\uab28-\\uab2e\\uabc0-\\uabe2\\uac00-\\ud7a3\\ud7b0-\\ud7c6\\ud7cb-\\ud7fb\\uf900-\\ufa6d\\ufa70-\\ufad9\\ufb00-\\ufb06\\ufb13-\\ufb17\\ufb1d\\ufb1f-\\ufb28\\ufb2a-\\ufb36\\ufb38-\\ufb3c\\ufb3e\\ufb40\\ufb41\\ufb43\\ufb44\\ufb46-\\ufbb1\\ufbd3-\\ufd3d\\ufd50-\\ufd8f\\ufd92-\\ufdc7\\ufdf0-\\ufdfb\\ufe70-\\ufe74\\ufe76-\\ufefc\\uff21-\\uff3a\\uff41-\\uff5a\\uff66-\\uffbe\\uffc2-\\uffc7\\uffca-\\uffcf\\uffd2-\\uffd7\\uffda-\\uffdc",
                u = "\\u0300-\\u036f\\u0483-\\u0487\\u0591-\\u05bd\\u05bf\\u05c1\\u05c2\\u05c4\\u05c5\\u05c7\\u0610-\\u061a\\u0620-\\u0649\\u0672-\\u06d3\\u06e7-\\u06e8\\u06fb-\\u06fc\\u0730-\\u074a\\u0800-\\u0814\\u081b-\\u0823\\u0825-\\u0827\\u0829-\\u082d\\u0840-\\u0857\\u08e4-\\u08fe\\u0900-\\u0903\\u093a-\\u093c\\u093e-\\u094f\\u0951-\\u0957\\u0962-\\u0963\\u0966-\\u096f\\u0981-\\u0983\\u09bc\\u09be-\\u09c4\\u09c7\\u09c8\\u09d7\\u09df-\\u09e0\\u0a01-\\u0a03\\u0a3c\\u0a3e-\\u0a42\\u0a47\\u0a48\\u0a4b-\\u0a4d\\u0a51\\u0a66-\\u0a71\\u0a75\\u0a81-\\u0a83\\u0abc\\u0abe-\\u0ac5\\u0ac7-\\u0ac9\\u0acb-\\u0acd\\u0ae2-\\u0ae3\\u0ae6-\\u0aef\\u0b01-\\u0b03\\u0b3c\\u0b3e-\\u0b44\\u0b47\\u0b48\\u0b4b-\\u0b4d\\u0b56\\u0b57\\u0b5f-\\u0b60\\u0b66-\\u0b6f\\u0b82\\u0bbe-\\u0bc2\\u0bc6-\\u0bc8\\u0bca-\\u0bcd\\u0bd7\\u0be6-\\u0bef\\u0c01-\\u0c03\\u0c46-\\u0c48\\u0c4a-\\u0c4d\\u0c55\\u0c56\\u0c62-\\u0c63\\u0c66-\\u0c6f\\u0c82\\u0c83\\u0cbc\\u0cbe-\\u0cc4\\u0cc6-\\u0cc8\\u0cca-\\u0ccd\\u0cd5\\u0cd6\\u0ce2-\\u0ce3\\u0ce6-\\u0cef\\u0d02\\u0d03\\u0d46-\\u0d48\\u0d57\\u0d62-\\u0d63\\u0d66-\\u0d6f\\u0d82\\u0d83\\u0dca\\u0dcf-\\u0dd4\\u0dd6\\u0dd8-\\u0ddf\\u0df2\\u0df3\\u0e34-\\u0e3a\\u0e40-\\u0e45\\u0e50-\\u0e59\\u0eb4-\\u0eb9\\u0ec8-\\u0ecd\\u0ed0-\\u0ed9\\u0f18\\u0f19\\u0f20-\\u0f29\\u0f35\\u0f37\\u0f39\\u0f41-\\u0f47\\u0f71-\\u0f84\\u0f86-\\u0f87\\u0f8d-\\u0f97\\u0f99-\\u0fbc\\u0fc6\\u1000-\\u1029\\u1040-\\u1049\\u1067-\\u106d\\u1071-\\u1074\\u1082-\\u108d\\u108f-\\u109d\\u135d-\\u135f\\u170e-\\u1710\\u1720-\\u1730\\u1740-\\u1750\\u1772\\u1773\\u1780-\\u17b2\\u17dd\\u17e0-\\u17e9\\u180b-\\u180d\\u1810-\\u1819\\u1920-\\u192b\\u1930-\\u193b\\u1951-\\u196d\\u19b0-\\u19c0\\u19c8-\\u19c9\\u19d0-\\u19d9\\u1a00-\\u1a15\\u1a20-\\u1a53\\u1a60-\\u1a7c\\u1a7f-\\u1a89\\u1a90-\\u1a99\\u1b46-\\u1b4b\\u1b50-\\u1b59\\u1b6b-\\u1b73\\u1bb0-\\u1bb9\\u1be6-\\u1bf3\\u1c00-\\u1c22\\u1c40-\\u1c49\\u1c5b-\\u1c7d\\u1cd0-\\u1cd2\\u1d00-\\u1dbe\\u1e01-\\u1f15\\u200c\\u200d\\u203f\\u2040\\u2054\\u20d0-\\u20dc\\u20e1\\u20e5-\\u20f0\\u2d81-\\u2d96\\u2de0-\\u2dff\\u3021-\\u3028\\u3099\\u309a\\ua640-\\ua66d\\ua674-\\ua67d\\ua69f\\ua6f0-\\ua6f1\\ua7f8-\\ua800\\ua806\\ua80b\\ua823-\\ua827\\ua880-\\ua881\\ua8b4-\\ua8c4\\ua8d0-\\ua8d9\\ua8f3-\\ua8f7\\ua900-\\ua909\\ua926-\\ua92d\\ua930-\\ua945\\ua980-\\ua983\\ua9b3-\\ua9c0\\uaa00-\\uaa27\\uaa40-\\uaa41\\uaa4c-\\uaa4d\\uaa50-\\uaa59\\uaa7b\\uaae0-\\uaae9\\uaaf2-\\uaaf3\\uabc0-\\uabe1\\uabec\\uabed\\uabf0-\\uabf9\\ufb20-\\ufb28\\ufe00-\\ufe0f\\ufe20-\\ufe26\\ufe33\\ufe34\\ufe4d-\\ufe4f\\uff10-\\uff19\\uff3f",
                _ = "(?:\\\\u[0-9a-fA-F]{4}|[\\x23\\x24\\x40\\x41-\\x5a\\x5f\\x61-\\x7a" + n + "])";
            e.identifier = new RegExp(_ + "(?:\\\\u[0-9a-fA-F]{4}|[\\x24\\x30-\\x39\\x41-\\x5a\\x5f\\x61-\\x7a\\xaa\\xb5\\xba\\xc0-\\xd6\\xd8-\\xf6\\xf8-\\u02c1\\u02c6-\\u02d1\\u02e0-\\u02e4\\u02ec\\u02ee\\u0370-\\u0374\\u0376\\u0377\\u037a-\\u037d\\u0386\\u0388-\\u038a\\u038c\\u038e-\\u03a1\\u03a3-\\u03f5\\u03f7-\\u0481\\u048a-\\u0527\\u0531-\\u0556\\u0559\\u0561-\\u0587\\u05d0-\\u05ea\\u05f0-\\u05f2\\u0620-\\u064a\\u066e\\u066f\\u0671-\\u06d3\\u06d5\\u06e5\\u06e6\\u06ee\\u06ef\\u06fa-\\u06fc\\u06ff\\u0710\\u0712-\\u072f\\u074d-\\u07a5\\u07b1\\u07ca-\\u07ea\\u07f4\\u07f5\\u07fa\\u0800-\\u0815\\u081a\\u0824\\u0828\\u0840-\\u0858\\u08a0\\u08a2-\\u08ac\\u0904-\\u0939\\u093d\\u0950\\u0958-\\u0961\\u0971-\\u0977\\u0979-\\u097f\\u0985-\\u098c\\u098f\\u0990\\u0993-\\u09a8\\u09aa-\\u09b0\\u09b2\\u09b6-\\u09b9\\u09bd\\u09ce\\u09dc\\u09dd\\u09df-\\u09e1\\u09f0\\u09f1\\u0a05-\\u0a0a\\u0a0f\\u0a10\\u0a13-\\u0a28\\u0a2a-\\u0a30\\u0a32\\u0a33\\u0a35\\u0a36\\u0a38\\u0a39\\u0a59-\\u0a5c\\u0a5e\\u0a72-\\u0a74\\u0a85-\\u0a8d\\u0a8f-\\u0a91\\u0a93-\\u0aa8\\u0aaa-\\u0ab0\\u0ab2\\u0ab3\\u0ab5-\\u0ab9\\u0abd\\u0ad0\\u0ae0\\u0ae1\\u0b05-\\u0b0c\\u0b0f\\u0b10\\u0b13-\\u0b28\\u0b2a-\\u0b30\\u0b32\\u0b33\\u0b35-\\u0b39\\u0b3d\\u0b5c\\u0b5d\\u0b5f-\\u0b61\\u0b71\\u0b83\\u0b85-\\u0b8a\\u0b8e-\\u0b90\\u0b92-\\u0b95\\u0b99\\u0b9a\\u0b9c\\u0b9e\\u0b9f\\u0ba3\\u0ba4\\u0ba8-\\u0baa\\u0bae-\\u0bb9\\u0bd0\\u0c05-\\u0c0c\\u0c0e-\\u0c10\\u0c12-\\u0c28\\u0c2a-\\u0c33\\u0c35-\\u0c39\\u0c3d\\u0c58\\u0c59\\u0c60\\u0c61\\u0c85-\\u0c8c\\u0c8e-\\u0c90\\u0c92-\\u0ca8\\u0caa-\\u0cb3\\u0cb5-\\u0cb9\\u0cbd\\u0cde\\u0ce0\\u0ce1\\u0cf1\\u0cf2\\u0d05-\\u0d0c\\u0d0e-\\u0d10\\u0d12-\\u0d3a\\u0d3d\\u0d4e\\u0d60\\u0d61\\u0d7a-\\u0d7f\\u0d85-\\u0d96\\u0d9a-\\u0db1\\u0db3-\\u0dbb\\u0dbd\\u0dc0-\\u0dc6\\u0e01-\\u0e30\\u0e32\\u0e33\\u0e40-\\u0e46\\u0e81\\u0e82\\u0e84\\u0e87\\u0e88\\u0e8a\\u0e8d\\u0e94-\\u0e97\\u0e99-\\u0e9f\\u0ea1-\\u0ea3\\u0ea5\\u0ea7\\u0eaa\\u0eab\\u0ead-\\u0eb0\\u0eb2\\u0eb3\\u0ebd\\u0ec0-\\u0ec4\\u0ec6\\u0edc-\\u0edf\\u0f00\\u0f40-\\u0f47\\u0f49-\\u0f6c\\u0f88-\\u0f8c\\u1000-\\u102a\\u103f\\u1050-\\u1055\\u105a-\\u105d\\u1061\\u1065\\u1066\\u106e-\\u1070\\u1075-\\u1081\\u108e\\u10a0-\\u10c5\\u10c7\\u10cd\\u10d0-\\u10fa\\u10fc-\\u1248\\u124a-\\u124d\\u1250-\\u1256\\u1258\\u125a-\\u125d\\u1260-\\u1288\\u128a-\\u128d\\u1290-\\u12b0\\u12b2-\\u12b5\\u12b8-\\u12be\\u12c0\\u12c2-\\u12c5\\u12c8-\\u12d6\\u12d8-\\u1310\\u1312-\\u1315\\u1318-\\u135a\\u1380-\\u138f\\u13a0-\\u13f4\\u1401-\\u166c\\u166f-\\u167f\\u1681-\\u169a\\u16a0-\\u16ea\\u16ee-\\u16f0\\u1700-\\u170c\\u170e-\\u1711\\u1720-\\u1731\\u1740-\\u1751\\u1760-\\u176c\\u176e-\\u1770\\u1780-\\u17b3\\u17d7\\u17dc\\u1820-\\u1877\\u1880-\\u18a8\\u18aa\\u18b0-\\u18f5\\u1900-\\u191c\\u1950-\\u196d\\u1970-\\u1974\\u1980-\\u19ab\\u19c1-\\u19c7\\u1a00-\\u1a16\\u1a20-\\u1a54\\u1aa7\\u1b05-\\u1b33\\u1b45-\\u1b4b\\u1b83-\\u1ba0\\u1bae\\u1baf\\u1bba-\\u1be5\\u1c00-\\u1c23\\u1c4d-\\u1c4f\\u1c5a-\\u1c7d\\u1ce9-\\u1cec\\u1cee-\\u1cf1\\u1cf5\\u1cf6\\u1d00-\\u1dbf\\u1e00-\\u1f15\\u1f18-\\u1f1d\\u1f20-\\u1f45\\u1f48-\\u1f4d\\u1f50-\\u1f57\\u1f59\\u1f5b\\u1f5d\\u1f5f-\\u1f7d\\u1f80-\\u1fb4\\u1fb6-\\u1fbc\\u1fbe\\u1fc2-\\u1fc4\\u1fc6-\\u1fcc\\u1fd0-\\u1fd3\\u1fd6-\\u1fdb\\u1fe0-\\u1fec\\u1ff2-\\u1ff4\\u1ff6-\\u1ffc\\u2071\\u207f\\u2090-\\u209c\\u2102\\u2107\\u210a-\\u2113\\u2115\\u2119-\\u211d\\u2124\\u2126\\u2128\\u212a-\\u212d\\u212f-\\u2139\\u213c-\\u213f\\u2145-\\u2149\\u214e\\u2160-\\u2188\\u2c00-\\u2c2e\\u2c30-\\u2c5e\\u2c60-\\u2ce4\\u2ceb-\\u2cee\\u2cf2\\u2cf3\\u2d00-\\u2d25\\u2d27\\u2d2d\\u2d30-\\u2d67\\u2d6f\\u2d80-\\u2d96\\u2da0-\\u2da6\\u2da8-\\u2dae\\u2db0-\\u2db6\\u2db8-\\u2dbe\\u2dc0-\\u2dc6\\u2dc8-\\u2dce\\u2dd0-\\u2dd6\\u2dd8-\\u2dde\\u2e2f\\u3005-\\u3007\\u3021-\\u3029\\u3031-\\u3035\\u3038-\\u303c\\u3041-\\u3096\\u309d-\\u309f\\u30a1-\\u30fa\\u30fc-\\u30ff\\u3105-\\u312d\\u3131-\\u318e\\u31a0-\\u31ba\\u31f0-\\u31ff\\u3400-\\u4db5\\u4e00-\\u9fcc\\ua000-\\ua48c\\ua4d0-\\ua4fd\\ua500-\\ua60c\\ua610-\\ua61f\\ua62a\\ua62b\\ua640-\\ua66e\\ua67f-\\ua697\\ua6a0-\\ua6ef\\ua717-\\ua71f\\ua722-\\ua788\\ua78b-\\ua78e\\ua790-\\ua793\\ua7a0-\\ua7aa\\ua7f8-\\ua801\\ua803-\\ua805\\ua807-\\ua80a\\ua80c-\\ua822\\ua840-\\ua873\\ua882-\\ua8b3\\ua8f2-\\ua8f7\\ua8fb\\ua90a-\\ua925\\ua930-\\ua946\\ua960-\\ua97c\\ua984-\\ua9b2\\ua9cf\\uaa00-\\uaa28\\uaa40-\\uaa42\\uaa44-\\uaa4b\\uaa60-\\uaa76\\uaa7a\\uaa80-\\uaaaf\\uaab1\\uaab5\\uaab6\\uaab9-\\uaabd\\uaac0\\uaac2\\uaadb-\\uaadd\\uaae0-\\uaaea\\uaaf2-\\uaaf4\\uab01-\\uab06\\uab09-\\uab0e\\uab11-\\uab16\\uab20-\\uab26\\uab28-\\uab2e\\uabc0-\\uabe2\\uac00-\\ud7a3\\ud7b0-\\ud7c6\\ud7cb-\\ud7fb\\uf900-\\ufa6d\\ufa70-\\ufad9\\ufb00-\\ufb06\\ufb13-\\ufb17\\ufb1d\\ufb1f-\\ufb28\\ufb2a-\\ufb36\\ufb38-\\ufb3c\\ufb3e\\ufb40\\ufb41\\ufb43\\ufb44\\ufb46-\\ufbb1\\ufbd3-\\ufd3d\\ufd50-\\ufd8f\\ufd92-\\ufdc7\\ufdf0-\\ufdfb\\ufe70-\\ufe74\\ufe76-\\ufefc\\uff21-\\uff3a\\uff41-\\uff5a\\uff66-\\uffbe\\uffc2-\\uffc7\\uffca-\\uffcf\\uffd2-\\uffd7\\uffda-\\uffdc\\u0300-\\u036f\\u0483-\\u0487\\u0591-\\u05bd\\u05bf\\u05c1\\u05c2\\u05c4\\u05c5\\u05c7\\u0610-\\u061a\\u0620-\\u0649\\u0672-\\u06d3\\u06e7-\\u06e8\\u06fb-\\u06fc\\u0730-\\u074a\\u0800-\\u0814\\u081b-\\u0823\\u0825-\\u0827\\u0829-\\u082d\\u0840-\\u0857\\u08e4-\\u08fe\\u0900-\\u0903\\u093a-\\u093c\\u093e-\\u094f\\u0951-\\u0957\\u0962-\\u0963\\u0966-\\u096f\\u0981-\\u0983\\u09bc\\u09be-\\u09c4\\u09c7\\u09c8\\u09d7\\u09df-\\u09e0\\u0a01-\\u0a03\\u0a3c\\u0a3e-\\u0a42\\u0a47\\u0a48\\u0a4b-\\u0a4d\\u0a51\\u0a66-\\u0a71\\u0a75\\u0a81-\\u0a83\\u0abc\\u0abe-\\u0ac5\\u0ac7-\\u0ac9\\u0acb-\\u0acd\\u0ae2-\\u0ae3\\u0ae6-\\u0aef\\u0b01-\\u0b03\\u0b3c\\u0b3e-\\u0b44\\u0b47\\u0b48\\u0b4b-\\u0b4d\\u0b56\\u0b57\\u0b5f-\\u0b60\\u0b66-\\u0b6f\\u0b82\\u0bbe-\\u0bc2\\u0bc6-\\u0bc8\\u0bca-\\u0bcd\\u0bd7\\u0be6-\\u0bef\\u0c01-\\u0c03\\u0c46-\\u0c48\\u0c4a-\\u0c4d\\u0c55\\u0c56\\u0c62-\\u0c63\\u0c66-\\u0c6f\\u0c82\\u0c83\\u0cbc\\u0cbe-\\u0cc4\\u0cc6-\\u0cc8\\u0cca-\\u0ccd\\u0cd5\\u0cd6\\u0ce2-\\u0ce3\\u0ce6-\\u0cef\\u0d02\\u0d03\\u0d46-\\u0d48\\u0d57\\u0d62-\\u0d63\\u0d66-\\u0d6f\\u0d82\\u0d83\\u0dca\\u0dcf-\\u0dd4\\u0dd6\\u0dd8-\\u0ddf\\u0df2\\u0df3\\u0e34-\\u0e3a\\u0e40-\\u0e45\\u0e50-\\u0e59\\u0eb4-\\u0eb9\\u0ec8-\\u0ecd\\u0ed0-\\u0ed9\\u0f18\\u0f19\\u0f20-\\u0f29\\u0f35\\u0f37\\u0f39\\u0f41-\\u0f47\\u0f71-\\u0f84\\u0f86-\\u0f87\\u0f8d-\\u0f97\\u0f99-\\u0fbc\\u0fc6\\u1000-\\u1029\\u1040-\\u1049\\u1067-\\u106d\\u1071-\\u1074\\u1082-\\u108d\\u108f-\\u109d\\u135d-\\u135f\\u170e-\\u1710\\u1720-\\u1730\\u1740-\\u1750\\u1772\\u1773\\u1780-\\u17b2\\u17dd\\u17e0-\\u17e9\\u180b-\\u180d\\u1810-\\u1819\\u1920-\\u192b\\u1930-\\u193b\\u1951-\\u196d\\u19b0-\\u19c0\\u19c8-\\u19c9\\u19d0-\\u19d9\\u1a00-\\u1a15\\u1a20-\\u1a53\\u1a60-\\u1a7c\\u1a7f-\\u1a89\\u1a90-\\u1a99\\u1b46-\\u1b4b\\u1b50-\\u1b59\\u1b6b-\\u1b73\\u1bb0-\\u1bb9\\u1be6-\\u1bf3\\u1c00-\\u1c22\\u1c40-\\u1c49\\u1c5b-\\u1c7d\\u1cd0-\\u1cd2\\u1d00-\\u1dbe\\u1e01-\\u1f15\\u200c\\u200d\\u203f\\u2040\\u2054\\u20d0-\\u20dc\\u20e1\\u20e5-\\u20f0\\u2d81-\\u2d96\\u2de0-\\u2dff\\u3021-\\u3028\\u3099\\u309a\\ua640-\\ua66d\\ua674-\\ua67d\\ua69f\\ua6f0-\\ua6f1\\ua7f8-\\ua800\\ua806\\ua80b\\ua823-\\ua827\\ua880-\\ua881\\ua8b4-\\ua8c4\\ua8d0-\\ua8d9\\ua8f3-\\ua8f7\\ua900-\\ua909\\ua926-\\ua92d\\ua930-\\ua945\\ua980-\\ua983\\ua9b3-\\ua9c0\\uaa00-\\uaa27\\uaa40-\\uaa41\\uaa4c-\\uaa4d\\uaa50-\\uaa59\\uaa7b\\uaae0-\\uaae9\\uaaf2-\\uaaf3\\uabc0-\\uabe1\\uabec\\uabed\\uabf0-\\uabf9\\ufb20-\\ufb28\\ufe00-\\ufe0f\\ufe20-\\ufe26\\ufe33\\ufe34\\ufe4d-\\ufe4f\\uff10-\\uff19\\uff3f])*", "g"), e.identifierStart = new RegExp(_), e.identifierMatch = new RegExp("(?:\\\\u[0-9a-fA-F]{4}|[" + i + n + u + "])+");
            e.newline = /[\n\r\u2028\u2029]/, e.lineBreak = new RegExp("\r\n|" + e.newline.source), e.allLineBreaks = new RegExp(e.lineBreak.source, "g")
        }, function (t, e, i) {
            var n = i(6).Options, u = ["before-newline", "after-newline", "preserve-newline"];

            function _(t) {
                n.call(this, t, "js");
                var t = this.raw_options.brace_style || null,
                    e = ("expand-strict" === t ? this.raw_options.brace_style = "expand" : "collapse-preserve-inline" === t ? this.raw_options.brace_style = "collapse,preserve-inline" : void 0 !== this.raw_options.braces_on_own_line && (this.raw_options.brace_style = this.raw_options.braces_on_own_line ? "expand" : "collapse"), this._get_selection_list("brace_style", ["collapse", "expand", "end-expand", "none", "preserve-inline"]));
                this.brace_preserve_inline = !1, this.brace_style = "collapse";
                for (var i = 0; i < e.length; i++) "preserve-inline" === e[i] ? this.brace_preserve_inline = !0 : this.brace_style = e[i];
                this.unindent_chained_methods = this._get_boolean("unindent_chained_methods"), this.break_chained_methods = this._get_boolean("break_chained_methods"), this.space_in_paren = this._get_boolean("space_in_paren"), this.space_in_empty_paren = this._get_boolean("space_in_empty_paren"), this.jslint_happy = this._get_boolean("jslint_happy"), this.space_after_anon_function = this._get_boolean("space_after_anon_function"), this.space_after_named_function = this._get_boolean("space_after_named_function"), this.keep_array_indentation = this._get_boolean("keep_array_indentation"), this.space_before_conditional = this._get_boolean("space_before_conditional", !0), this.unescape_strings = this._get_boolean("unescape_strings"), this.e4x = this._get_boolean("e4x"), this.comma_first = this._get_boolean("comma_first"), this.operator_position = this._get_selection("operator_position", u), this.test_output_raw = this._get_boolean("test_output_raw"), this.jslint_happy && (this.space_after_anon_function = !0)
            }

            _.prototype = new n, t.exports.Options = _
        }, function (t) {
            function e(t, e) {
                this.raw_options = i(t, e), this.disabled = this._get_boolean("disabled"), this.eol = this._get_characters("eol", "auto"), this.end_with_newline = this._get_boolean("end_with_newline"), this.indent_size = this._get_number("indent_size", 4), this.indent_char = this._get_characters("indent_char", " "), this.indent_level = this._get_number("indent_level"), this.preserve_newlines = this._get_boolean("preserve_newlines", !0), this.max_preserve_newlines = this._get_number("max_preserve_newlines", 32786), this.preserve_newlines || (this.max_preserve_newlines = 0), this.indent_with_tabs = this._get_boolean("indent_with_tabs", "\t" === this.indent_char), this.indent_with_tabs && (this.indent_char = "\t", 1 === this.indent_size) && (this.indent_size = 4), this.wrap_line_length = this._get_number("wrap_line_length", this._get_number("max_char")), this.indent_empty_lines = this._get_boolean("indent_empty_lines"), this.templating = this._get_selection_list("templating", ["auto", "none", "django", "erb", "handlebars", "php", "smarty"], ["auto"])
            }

            function i(t, e) {
                var i, n = {};
                for (i in t = u(t)) i !== e && (n[i] = t[i]);
                if (e && t[e]) for (i in t[e]) n[i] = t[e][i];
                return n
            }

            function u(t) {
                var e, i = {};
                for (e in t) i[e.replace(/-/g, "_")] = t[e];
                return i
            }

            e.prototype._get_array = function (t, e) {
                t = this.raw_options[t], e = e || [];
                return "object" == typeof t ? null !== t && "function" == typeof t.concat && (e = t.concat()) : "string" == typeof t && (e = t.split(/[^a-zA-Z0-9_\/\-]+/)), e
            }, e.prototype._get_boolean = function (t, e) {
                t = this.raw_options[t];
                return void 0 === t ? !!e : !!t
            }, e.prototype._get_characters = function (t, e) {
                t = this.raw_options[t], e = e || "";
                return e = "string" == typeof t ? t.replace(/\\r/, "\r").replace(/\\n/, "\n").replace(/\\t/, "\t") : e
            }, e.prototype._get_number = function (t, e) {
                t = this.raw_options[t], e = parseInt(e, 10), isNaN(e) && (e = 0), t = parseInt(t, 10);
                return t = isNaN(t) ? e : t
            }, e.prototype._get_selection = function (t, e, i) {
                i = this._get_selection_list(t, e, i);
                if (1 !== i.length) throw new Error("Invalid Option Value: The option '" + t + "' can only be one of the following values:\n" + e + "\nYou passed in: '" + this.raw_options[t] + "'");
                return i[0]
            }, e.prototype._get_selection_list = function (t, e, i) {
                if (!e || 0 === e.length) throw new Error("Selection list cannot be empty.");
                if (i = i || [e[0]], !this._is_valid_selection(i, e)) throw new Error("Invalid Default Value!");
                i = this._get_array(t, i);
                if (this._is_valid_selection(i, e)) return i;
                throw new Error("Invalid Option Value: The option '" + t + "' can contain only the following values:\n" + e + "\nYou passed in: '" + this.raw_options[t] + "'")
            }, e.prototype._is_valid_selection = function (t, e) {
                return t.length && e.length && !t.some(function (t) {
                    return -1 === e.indexOf(t)
                })
            }, t.exports.Options = e, t.exports.normalizeOpts = u, t.exports.mergeOpts = i
        }, function (t, e, i) {
            var _ = i(8).InputScanner, n = i(9).Tokenizer, u = i(9).TOKEN, s = i(13).Directives, r = i(4),
                a = i(12).Pattern, o = i(14).TemplatablePattern;

            function h(t, e) {
                return -1 !== e.indexOf(t)
            }

            function p(t, e) {
                n.call(this, t, e), this._patterns.whitespace = this._patterns.whitespace.matching(/\u00A0\u1680\u180e\u2000-\u200a\u202f\u205f\u3000\ufeff/.source, /\u2028\u2029/.source), t = new a(this._input), e = new o(this._input).read_options(this._options), this.__patterns = {
                    template: e,
                    identifier: e.starting_with(r.identifier).matching(r.identifierMatch),
                    number: t.matching(d),
                    punct: t.matching(k),
                    comment: t.starting_with(/\/\//).until(/[\n\r\u2028\u2029]/),
                    block_comment: t.starting_with(/\/\*/).until_after(/\*\//),
                    html_comment_start: t.matching(/<!--/),
                    html_comment_end: t.matching(/-->/),
                    include: t.starting_with(/#include/).until_after(r.lineBreak),
                    shebang: t.starting_with(/#!/).until_after(r.lineBreak),
                    xml: t.matching(/[\s\S]*?<(\/?)([-a-zA-Z:0-9_.]+|{[^}]+?}|!\[CDATA\[[^\]]*?\]\]|)(\s*{[^}]+?}|\s+[-a-zA-Z:0-9_.]+|\s+[-a-zA-Z:0-9_.]+\s*=\s*('[^']*'|"[^"]*"|{([^{}]|{[^}]+?})+?}))*\s*(\/?)\s*>/),
                    single_quote: e.until(/['\\\n\r\u2028\u2029]/),
                    double_quote: e.until(/["\\\n\r\u2028\u2029]/),
                    template_text: e.until(/[`\\$]/),
                    template_expression: e.until(/[`}\\]/)
                }
            }

            var l, f = {
                    START_EXPR: "TK_START_EXPR",
                    END_EXPR: "TK_END_EXPR",
                    START_BLOCK: "TK_START_BLOCK",
                    END_BLOCK: "TK_END_BLOCK",
                    WORD: "TK_WORD",
                    RESERVED: "TK_RESERVED",
                    SEMICOLON: "TK_SEMICOLON",
                    STRING: "TK_STRING",
                    EQUALS: "TK_EQUALS",
                    OPERATOR: "TK_OPERATOR",
                    COMMA: "TK_COMMA",
                    BLOCK_COMMENT: "TK_BLOCK_COMMENT",
                    COMMENT: "TK_COMMENT",
                    DOT: "TK_DOT",
                    UNKNOWN: "TK_UNKNOWN",
                    START: u.START,
                    RAW: u.RAW,
                    EOF: u.EOF
                }, c = new s(/\/\*/, /\*\//),
                d = /0[xX][0123456789abcdefABCDEF_]*n?|0[oO][01234567_]*n?|0[bB][01_]*n?|\d[\d_]*n|(?:\.\d[\d_]*|\d[\d_]*\.?[\d_]*)(?:[eE][+-]?[\d_]+)?/,
                b = /[0-9]/, g = /[^\d\.]/,
                i = ">>> === !== &&= ??= ||= << && >= ** != == <= >> || ?? |> < / - + > : & % ? ^ | *".split(" "),
                u = ">>>= ... >>= <<= === >>> !== **= &&= ??= ||= => ^= :: /= << <= == && -= >= >> != -- += ** || ?? ++ %= &= *= |= |> = ! ? > < : / ^ - + * & % ~ |",
                k = (u = (u = "\\?\\.(?!\\d) " + (u = u.replace(/[-[\]{}()*+?.,\\^$|#]/g, "\\$&"))).replace(/ /g, "|"), new RegExp(u)),
                s = "continue,try,throw,return,var,let,const,if,switch,case,default,for,while,break,function,import,export".split(","),
                u = s.concat(["do", "in", "of", "else", "get", "set", "new", "catch", "finally", "typeof", "yield", "async", "await", "from", "as", "class", "extends"]),
                m = new RegExp("^(?:" + u.join("|") + ")$");
            (p.prototype = new n)._is_comment = function (t) {
                return t.type === f.COMMENT || t.type === f.BLOCK_COMMENT || t.type === f.UNKNOWN
            }, p.prototype._is_opening = function (t) {
                return t.type === f.START_BLOCK || t.type === f.START_EXPR
            }, p.prototype._is_closing = function (t, e) {
                return (t.type === f.END_BLOCK || t.type === f.END_EXPR) && e && ("]" === t.text && "[" === e.text || ")" === t.text && "(" === e.text || "}" === t.text && "{" === e.text)
            }, p.prototype._reset = function () {
                l = !1
            }, p.prototype._get_next_token = function (t, e) {
                this._readWhitespace();
                var i = this._input.peek();
                return null === i ? this._create_token(f.EOF, "") : this._read_non_javascript(i) || this._read_string(i) || this._read_pair(i, this._input.peek(1)) || this._read_word(t) || this._read_singles(i) || this._read_comment(i) || this._read_regexp(i, t) || this._read_xml(i, t) || this._read_punctuation() || this._create_token(f.UNKNOWN, this._input.next())
            }, p.prototype._read_word = function (t) {
                var e = this.__patterns.identifier.read();
                return "" !== e ? (e = e.replace(r.allLineBreaks, "\n"), t.type !== f.DOT && (t.type !== f.RESERVED || "set" !== t.text && "get" !== t.text) && m.test(e) ? "in" !== e && "of" !== e || t.type !== f.WORD && t.type !== f.STRING ? this._create_token(f.RESERVED, e) : this._create_token(f.OPERATOR, e) : this._create_token(f.WORD, e)) : "" !== (e = this.__patterns.number.read()) ? this._create_token(f.WORD, e) : void 0
            }, p.prototype._read_singles = function (t) {
                var e = null;
                return "(" === t || "[" === t ? e = this._create_token(f.START_EXPR, t) : ")" === t || "]" === t ? e = this._create_token(f.END_EXPR, t) : "{" === t ? e = this._create_token(f.START_BLOCK, t) : "}" === t ? e = this._create_token(f.END_BLOCK, t) : ";" === t ? e = this._create_token(f.SEMICOLON, t) : "." === t && g.test(this._input.peek(1)) ? e = this._create_token(f.DOT, t) : "," === t && (e = this._create_token(f.COMMA, t)), e && this._input.next(), e
            }, p.prototype._read_pair = function (t, e) {
                var i = null;
                return (i = "#" === t && "{" === e ? this._create_token(f.START_BLOCK, t + e) : i) && (this._input.next(), this._input.next()), i
            }, p.prototype._read_punctuation = function () {
                var t = this.__patterns.punct.read();
                if ("" !== t) return "=" === t ? this._create_token(f.EQUALS, t) : "?." === t ? this._create_token(f.DOT, t) : this._create_token(f.OPERATOR, t)
            }, p.prototype._read_non_javascript = function (t) {
                var e = "";
                if ("#" === t) {
                    if (this._is_first_token() && (e = this.__patterns.shebang.read())) return this._create_token(f.UNKNOWN, e.trim() + "\n");
                    if (e = this.__patterns.include.read()) return this._create_token(f.UNKNOWN, e.trim() + "\n");
                    t = this._input.next();
                    var i = "#";
                    if (this._input.hasNext() && this._input.testChar(b)) {
                        for (; i += t = this._input.next(), this._input.hasNext() && "#" !== t && "=" !== t;) ;
                        return "#" !== t && ("[" === this._input.peek() && "]" === this._input.peek(1) ? (i += "[]", this._input.next(), this._input.next()) : "{" === this._input.peek() && "}" === this._input.peek(1) && (i += "{}", this._input.next(), this._input.next())), this._create_token(f.WORD, i)
                    }
                    this._input.back()
                } else if ("<" === t && this._is_first_token()) {
                    if (e = this.__patterns.html_comment_start.read()) {
                        for (; this._input.hasNext() && !this._input.testChar(r.newline);) e += this._input.next();
                        return l = !0, this._create_token(f.COMMENT, e)
                    }
                } else if (l && "-" === t && (e = this.__patterns.html_comment_end.read())) return l = !1, this._create_token(f.COMMENT, e);
                return null
            }, p.prototype._read_comment = function (t) {
                var e, i = null;
                return "/" === t && (t = "", "*" === this._input.peek(1) ? (t = this.__patterns.block_comment.read(), (e = c.get_directives(t)) && "start" === e.ignore && (t += c.readIgnored(this._input)), t = t.replace(r.allLineBreaks, "\n"), (i = this._create_token(f.BLOCK_COMMENT, t)).directives = e) : "/" === this._input.peek(1) && (t = this.__patterns.comment.read(), i = this._create_token(f.COMMENT, t))), i
            }, p.prototype._read_string = function (t) {
                var e;
                return "`" === t || "'" === t || '"' === t ? (e = this._input.next(), this.has_char_escapes = !1, e += "`" === t ? this._read_string_recursive("`", !0, "${") : this._read_string_recursive(t), this.has_char_escapes && this._options.unescape_strings && (e = function (t) {
                    var e = "", i = 0, n = new _(t), u = null;
                    for (; n.hasNext();) if ((u = n.match(/([\s]|[^\\]|\\\\)+/g)) && (e += u[0]), "\\" === n.peek()) {
                        if (n.next(), "x" === n.peek()) u = n.match(/x([0-9A-Fa-f]{2})/g); else {
                            if ("u" !== n.peek()) {
                                e += "\\", n.hasNext() && (e += n.next());
                                continue
                            }
                            u = n.match(/u([0-9A-Fa-f]{4})/g)
                        }
                        if (!u) return t;
                        if (126 < (i = parseInt(u[1], 16)) && i <= 255 && 0 === u[0].indexOf("x")) return t;
                        e += 0 <= i && i < 32 ? "\\" + u[0] : 34 === i || 39 === i || 92 === i ? "\\" + String.fromCharCode(i) : String.fromCharCode(i)
                    }
                    return e
                }(e)), this._input.peek() === t && (e += this._input.next()), e = e.replace(r.allLineBreaks, "\n"), this._create_token(f.STRING, e)) : null
            }, p.prototype._allow_regexp_or_xml = function (t) {
                return t.type === f.RESERVED && h(t.text, ["return", "case", "throw", "else", "do", "typeof", "yield"]) || t.type === f.END_EXPR && ")" === t.text && t.opened.previous.type === f.RESERVED && h(t.opened.previous.text, ["if", "while", "for"]) || h(t.type, [f.COMMENT, f.START_EXPR, f.START_BLOCK, f.START, f.END_BLOCK, f.OPERATOR, f.EQUALS, f.EOF, f.SEMICOLON, f.COMMA])
            }, p.prototype._read_regexp = function (t, e) {
                if ("/" === t && this._allow_regexp_or_xml(e)) {
                    for (var i = this._input.next(), n = !1, u = !1; this._input.hasNext() && (n || u || this._input.peek() !== t) && !this._input.testChar(r.newline);) i += this._input.peek(), n ? n = !1 : (n = "\\" === this._input.peek(), "[" === this._input.peek() ? u = !0 : "]" === this._input.peek() && (u = !1)), this._input.next();
                    return this._input.peek() === t && (i = (i += this._input.next()) + this._input.read(r.identifier)), this._create_token(f.STRING, i)
                }
                return null
            }, p.prototype._read_xml = function (t, e) {
                if (this._options.e4x && "<" === t && this._allow_regexp_or_xml(e)) {
                    var i = "", n = this.__patterns.xml.read_match();
                    if (n) {
                        for (var u = n[2].replace(/^{\s+/, "{").replace(/\s+}$/, "}"), _ = 0 === u.indexOf("{"), s = 0; n;) {
                            var a = !!n[1], o = n[2];
                            if (!(!!n[n.length - 1] || "![CDATA[" === o.slice(0, 8)) && (o === u || _ && o.replace(/^{\s+/, "{").replace(/\s+}$/, "}")) && (a ? --s : ++s), i += n[0], s <= 0) break;
                            n = this.__patterns.xml.read_match()
                        }
                        return n || (i += this._input.match(/[\s\S]*/g)[0]), i = i.replace(r.allLineBreaks, "\n"), this._create_token(f.STRING, i)
                    }
                }
                return null
            }, p.prototype._read_string_recursive = function (t, e, i) {
                "'" === t ? u = this.__patterns.single_quote : '"' === t ? u = this.__patterns.double_quote : "`" === t ? u = this.__patterns.template_text : "}" === t && (u = this.__patterns.template_expression);
                for (var n, u, _ = u.read(), s = ""; this._input.hasNext();) {
                    if ((s = this._input.next()) === t || !e && r.newline.test(s)) {
                        this._input.back();
                        break
                    }
                    "\\" === s && this._input.hasNext() ? ("x" === (n = this._input.peek()) || "u" === n ? this.has_char_escapes = !0 : "\r" === n && "\n" === this._input.peek(1) && this._input.next(), s += this._input.next()) : i && ("${" === i && "$" === s && "{" === this._input.peek() && (s += this._input.next()), i === s) && (s += "`" === t ? this._read_string_recursive("}", e, "`") : this._read_string_recursive("`", e, "${"), this._input.hasNext()) && (s += this._input.next()), _ += s += u.read()
                }
                return _
            }, t.exports.Tokenizer = p, t.exports.TOKEN = f, t.exports.positionable_operators = i.slice(), t.exports.line_starters = s.slice()
        }, function (t) {
            var n = RegExp.prototype.hasOwnProperty("sticky");

            function e(t) {
                this.__input = t || "", this.__input_length = this.__input.length, this.__position = 0
            }

            e.prototype.restart = function () {
                this.__position = 0
            }, e.prototype.back = function () {
                0 < this.__position && --this.__position
            }, e.prototype.hasNext = function () {
                return this.__position < this.__input_length
            }, e.prototype.next = function () {
                var t = null;
                return this.hasNext() && (t = this.__input.charAt(this.__position), this.__position += 1), t
            }, e.prototype.peek = function (t) {
                var e = null;
                return t = t || 0, e = 0 <= (t += this.__position) && t < this.__input_length ? this.__input.charAt(t) : e
            }, e.prototype.__match = function (t, e) {
                t.lastIndex = e;
                var i = t.exec(this.__input);
                return !i || n && t.sticky || i.index !== e && (i = null), i
            }, e.prototype.test = function (t, e) {
                return e = e || 0, 0 <= (e += this.__position) && e < this.__input_length && !!this.__match(t, e)
            }, e.prototype.testChar = function (t, e) {
                e = this.peek(e);
                return t.lastIndex = 0, null !== e && t.test(e)
            }, e.prototype.match = function (t) {
                t = this.__match(t, this.__position);
                return t ? this.__position += t[0].length : t = null, t
            }, e.prototype.read = function (t, e, i) {
                var n, u = "";
                return t && (n = this.match(t)) && (u += n[0]), !e || !n && t || (u += this.readUntil(e, i)), u
            }, e.prototype.readUntil = function (t, e) {
                var i = this.__position, t = (t.lastIndex = this.__position, t.exec(this.__input));
                return t ? (i = t.index, e && (i += t[0].length)) : i = this.__input_length, e = this.__input.substring(this.__position, i), this.__position = i, e
            }, e.prototype.readUntilAfter = function (t) {
                return this.readUntil(t, !0)
            }, e.prototype.get_regexp = function (t, e) {
                var i = null, e = e && n ? "y" : "g";
                return "string" == typeof t && "" !== t ? i = new RegExp(t, e) : t && (i = new RegExp(t.source, e)), i
            }, e.prototype.get_literal_regexp = function (t) {
                return RegExp(t.replace(/[-\/\\^$*+?.()|[\]{}]/g, "\\$&"))
            }, e.prototype.peekUntilAfter = function (t) {
                var e = this.__position, t = this.readUntilAfter(t);
                return this.__position = e, t
            }, e.prototype.lookBack = function (t) {
                var e = this.__position - 1;
                return e >= t.length && this.__input.substring(e - t.length, e).toLowerCase() === t
            }, t.exports.InputScanner = e
        }, function (t, e, i) {
            function n(t, e) {
                this._input = new u(t), this._options = e || {}, this.__tokens = null, this._patterns = {}, this._patterns.whitespace = new a(this._input)
            }

            var u = i(8).InputScanner, _ = i(3).Token, s = i(10).TokenStream, a = i(11).WhitespacePattern,
                o = {START: "TK_START", RAW: "TK_RAW", EOF: "TK_EOF"};
            n.prototype.tokenize = function () {
                this._input.restart(), this.__tokens = new s, this._reset();
                for (var t, e = new _(o.START, ""), i = null, n = [], u = new s; e.type !== o.EOF;) {
                    for (t = this._get_next_token(e, i); this._is_comment(t);) u.add(t), t = this._get_next_token(e, i);
                    u.isEmpty() || (t.comments_before = u, u = new s), t.parent = i, this._is_opening(t) ? (n.push(i), i = t) : i && this._is_closing(t, i) && ((t.opened = i).closed = t, i = n.pop(), t.parent = i), (t.previous = e).next = t, this.__tokens.add(t), e = t
                }
                return this.__tokens
            }, n.prototype._is_first_token = function () {
                return this.__tokens.isEmpty()
            }, n.prototype._reset = function () {
            }, n.prototype._get_next_token = function (t, e) {
                this._readWhitespace();
                var i = this._input.read(/.+/g);
                return i ? this._create_token(o.RAW, i) : this._create_token(o.EOF, "")
            }, n.prototype._is_comment = function (t) {
                return !1
            }, n.prototype._is_opening = function (t) {
                return !1
            }, n.prototype._is_closing = function (t, e) {
                return !1
            }, n.prototype._create_token = function (t, e) {
                return new _(t, e, this._patterns.whitespace.newline_count, this._patterns.whitespace.whitespace_before_token)
            }, n.prototype._readWhitespace = function () {
                return this._patterns.whitespace.read()
            }, t.exports.Tokenizer = n, t.exports.TOKEN = o
        }, function (t) {
            function e(t) {
                this.__tokens = [], this.__tokens_length = this.__tokens.length, this.__position = 0, this.__parent_token = t
            }

            e.prototype.restart = function () {
                this.__position = 0
            }, e.prototype.isEmpty = function () {
                return 0 === this.__tokens_length
            }, e.prototype.hasNext = function () {
                return this.__position < this.__tokens_length
            }, e.prototype.next = function () {
                var t = null;
                return this.hasNext() && (t = this.__tokens[this.__position], this.__position += 1), t
            }, e.prototype.peek = function (t) {
                var e = null;
                return t = t || 0, e = 0 <= (t += this.__position) && t < this.__tokens_length ? this.__tokens[t] : e
            }, e.prototype.add = function (t) {
                this.__parent_token && (t.parent = this.__parent_token), this.__tokens.push(t), this.__tokens_length += 1
            }, t.exports.TokenStream = e
        }, function (t, e, i) {
            var n = i(12).Pattern;

            function u(t, e) {
                n.call(this, t, e), e ? this._line_regexp = this._input.get_regexp(e._line_regexp) : this.__set_whitespace_patterns("", ""), this.newline_count = 0, this.whitespace_before_token = ""
            }

            (u.prototype = new n).__set_whitespace_patterns = function (t, e) {
                this._match_pattern = this._input.get_regexp("[" + (t += "\\t ") + (e += "\\n\\r") + "]+", !0), this._newline_regexp = this._input.get_regexp("\\r\\n|[" + e + "]")
            }, u.prototype.read = function () {
                this.newline_count = 0, this.whitespace_before_token = "";
                var t, e = this._input.read(this._match_pattern);
                return " " === e ? this.whitespace_before_token = " " : e && (t = this.__split(this._newline_regexp, e), this.newline_count = t.length - 1, this.whitespace_before_token = t[this.newline_count]), e
            }, u.prototype.matching = function (t, e) {
                var i = this._create();
                return i.__set_whitespace_patterns(t, e), i._update(), i
            }, u.prototype._create = function () {
                return new u(this._input, this)
            }, u.prototype.__split = function (t, e) {
                for (var i = t.lastIndex = 0, n = [], u = t.exec(e); u;) n.push(e.substring(i, u.index)), i = u.index + u[0].length, u = t.exec(e);
                return i < e.length ? n.push(e.substring(i, e.length)) : n.push(""), n
            }, t.exports.WhitespacePattern = u
        }, function (t) {
            function e(t, e) {
                this._input = t, this._starting_pattern = null, this._match_pattern = null, this._until_pattern = null, this._until_after = !1, e && (this._starting_pattern = this._input.get_regexp(e._starting_pattern, !0), this._match_pattern = this._input.get_regexp(e._match_pattern, !0), this._until_pattern = this._input.get_regexp(e._until_pattern), this._until_after = e._until_after)
            }

            e.prototype.read = function () {
                var t = this._input.read(this._starting_pattern);
                return this._starting_pattern && !t || (t += this._input.read(this._match_pattern, this._until_pattern, this._until_after)), t
            }, e.prototype.read_match = function () {
                return this._input.match(this._match_pattern)
            }, e.prototype.until_after = function (t) {
                var e = this._create();
                return e._until_after = !0, e._until_pattern = this._input.get_regexp(t), e._update(), e
            }, e.prototype.until = function (t) {
                var e = this._create();
                return e._until_after = !1, e._until_pattern = this._input.get_regexp(t), e._update(), e
            }, e.prototype.starting_with = function (t) {
                var e = this._create();
                return e._starting_pattern = this._input.get_regexp(t, !0), e._update(), e
            }, e.prototype.matching = function (t) {
                var e = this._create();
                return e._match_pattern = this._input.get_regexp(t, !0), e._update(), e
            }, e.prototype._create = function () {
                return new e(this._input, this)
            }, e.prototype._update = function () {
            }, t.exports.Pattern = e
        }, function (t) {
            function e(t, e) {
                t = "string" == typeof t ? t : t.source, e = "string" == typeof e ? e : e.source, this.__directives_block_pattern = new RegExp(t + / beautify( \w+[:]\w+)+ /.source + e, "g"), this.__directive_pattern = / (\w+)[:](\w+)/g, this.__directives_end_ignore_pattern = new RegExp(t + /\sbeautify\signore:end\s/.source + e, "g")
            }

            e.prototype.get_directives = function (t) {
                if (!t.match(this.__directives_block_pattern)) return null;
                for (var e = {}, i = (this.__directive_pattern.lastIndex = 0, this.__directive_pattern.exec(t)); i;) e[i[1]] = i[2], i = this.__directive_pattern.exec(t);
                return e
            }, e.prototype.readIgnored = function (t) {
                return t.readUntilAfter(this.__directives_end_ignore_pattern)
            }, t.exports.Directives = e
        }, function (t, e, i) {
            var n = i(12).Pattern, u = {django: !1, erb: !1, handlebars: !1, php: !1, smarty: !1};

            function _(t, e) {
                n.call(this, t, e), this.__template_pattern = null, this._disabled = Object.assign({}, u), this._excluded = Object.assign({}, u), e && (this.__template_pattern = this._input.get_regexp(e.__template_pattern), this._excluded = Object.assign(this._excluded, e._excluded), this._disabled = Object.assign(this._disabled, e._disabled));
                e = new n(t);
                this.__patterns = {
                    handlebars_comment: e.starting_with(/{{!--/).until_after(/--}}/),
                    handlebars_unescaped: e.starting_with(/{{{/).until_after(/}}}/),
                    handlebars: e.starting_with(/{{/).until_after(/}}/),
                    php: e.starting_with(/<\?(?:[= ]|php)/).until_after(/\?>/),
                    erb: e.starting_with(/<%[^%]/).until_after(/[^%]%>/),
                    django: e.starting_with(/{%/).until_after(/%}/),
                    django_value: e.starting_with(/{{/).until_after(/}}/),
                    django_comment: e.starting_with(/{#/).until_after(/#}/),
                    smarty: e.starting_with(/{(?=[^}{\s\n])/).until_after(/[^\s\n]}/),
                    smarty_comment: e.starting_with(/{\*/).until_after(/\*}/),
                    smarty_literal: e.starting_with(/{literal}/).until_after(/{\/literal}/)
                }
            }

            (_.prototype = new n)._create = function () {
                return new _(this._input, this)
            }, _.prototype._update = function () {
                this.__set_templated_pattern()
            }, _.prototype.disable = function (t) {
                var e = this._create();
                return e._disabled[t] = !0, e._update(), e
            }, _.prototype.read_options = function (t) {
                var e, i = this._create();
                for (e in u) i._disabled[e] = -1 === t.templating.indexOf(e);
                return i._update(), i
            }, _.prototype.exclude = function (t) {
                var e = this._create();
                return e._excluded[t] = !0, e._update(), e
            }, _.prototype.read = function () {
                for (var t = "", t = this._match_pattern ? this._input.read(this._starting_pattern) : this._input.read(this._starting_pattern, this.__template_pattern), e = this._read_template(); e;) this._match_pattern ? e += this._input.read(this._match_pattern) : e += this._input.readUntil(this.__template_pattern), t += e, e = this._read_template();
                return this._until_after && (t += this._input.readUntilAfter(this._until_pattern)), t
            }, _.prototype.__set_templated_pattern = function () {
                var t = [];
                this._disabled.php || t.push(this.__patterns.php._starting_pattern.source), this._disabled.handlebars || t.push(this.__patterns.handlebars._starting_pattern.source), this._disabled.erb || t.push(this.__patterns.erb._starting_pattern.source), this._disabled.django || (t.push(this.__patterns.django._starting_pattern.source), t.push(this.__patterns.django_value._starting_pattern.source), t.push(this.__patterns.django_comment._starting_pattern.source)), this._disabled.smarty || t.push(this.__patterns.smarty._starting_pattern.source), this._until_pattern && t.push(this._until_pattern.source), this.__template_pattern = this._input.get_regexp("(?:" + t.join("|") + ")")
            }, _.prototype._read_template = function () {
                var t, e = "", i = this._input.peek();
                return "<" === i ? (t = this._input.peek(1), this._disabled.php || this._excluded.php || "?" !== t || (e = e || this.__patterns.php.read()), this._disabled.erb || this._excluded.erb || "%" !== t || (e = e || this.__patterns.erb.read())) : "{" === i && (this._disabled.handlebars || this._excluded.handlebars || (e = (e = (e = e || this.__patterns.handlebars_comment.read()) || this.__patterns.handlebars_unescaped.read()) || this.__patterns.handlebars.read()), this._disabled.django || (this._excluded.django || this._excluded.handlebars || (e = e || this.__patterns.django_value.read()), this._excluded.django) || (e = (e = e || this.__patterns.django_comment.read()) || this.__patterns.django.read()), this._disabled.smarty || this._disabled.django && this._disabled.handlebars && (e = (e = (e = e || this.__patterns.smarty_comment.read()) || this.__patterns.smarty_literal.read()) || this.__patterns.smarty.read())), e
            }, t.exports.TemplatablePattern = _
        }], u = {};
        var t = function t(e) {
            var i = u[e];
            return void 0 === i && (i = u[e] = {exports: {}}, n[e](i, i.exports, t)), i.exports
        }(0);
        e = t
    }();
    var e, t = e;
    "function" == typeof define && define.amd ? define([], function () {
        return {js_beautify: t}
    }) : "undefined" != typeof exports ? exports.js_beautify = t : "undefined" != typeof window ? window.js_beautify = t : "undefined" != typeof global && (global.js_beautify = t)
}();

/* Object.assign polyfill */
!function r(e, t, n) {
    function o(u, f) {
        if (!t[u]) {
            if (!e[u]) {
                var c = "function" == typeof require && require;
                if (!f && c) return c(u, !0);
                if (i) return i(u, !0);
                var a = new Error("Cannot find module '" + u + "'");
                throw a.code = "MODULE_NOT_FOUND", a
            }
            var l = t[u] = {exports: {}};
            e[u][0].call(l.exports, function (r) {
                var t = e[u][1][r];
                return o(t ? t : r)
            }, l, l.exports, r, e, t, n)
        }
        return t[u].exports
    }

    for (var i = "function" == typeof require && require, u = 0; u < n.length; u++) o(n[u]);
    return o
}({
    1: [function (r, e, t) {
        "use strict";
        r("./index").polyfill()
    }, {"./index": 2}], 2: [function (r, e, t) {
        "use strict";

        function n(r, e) {
            if (void 0 === r || null === r) throw new TypeError("Cannot convert first argument to object");
            for (var t = Object(r), n = 1; n < arguments.length; n++) {
                var o = arguments[n];
                if (void 0 !== o && null !== o) for (var i = Object.keys(Object(o)), u = 0, f = i.length; u < f; u++) {
                    var c = i[u], a = Object.getOwnPropertyDescriptor(o, c);
                    void 0 !== a && a.enumerable && (t[c] = o[c])
                }
            }
            return t
        }

        function o() {
            Object.assign || Object.defineProperty(Object, "assign", {
                enumerable: !1,
                configurable: !0,
                writable: !0,
                value: n
            })
        }

        e.exports = {assign: n, polyfill: o}
    }, {}]
}, {}, [1]);