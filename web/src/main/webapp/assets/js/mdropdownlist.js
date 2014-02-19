(function () {
    var readyFn = [];

    function ready(fn) {
        readyFn[readyFn.length] = fn;
    }

    window.onload = function () {
        for (var i = 0; i < readyFn.length; i++) {
            readyFn[i].call();
        }
    }
    window['ready'] = ready;

    function queryElement(s) {
        if (typeof s == 'string') {
            if (s.substr(0, 1) == '#') {
                return document.getElementById(s.substr(1));
            }
        }
    }

    window['q'] = queryElement;
})();

(function () {

    function toggle(item) {
        item.style.display =
            (item.style.display == 'none') ? 'block' : 'none';
    }

    function hide(item) {
        item.style.display = 'none';
    }

    function get_index(id) {
        // id : #per-item-1
        var arr = id.split('-');
        return parseInt(arr[arr.length - 1]);
    }

    function html(id, opts) {
        var html = '<div class="chzn-drop"style="width: 218px;" id="' + id + '-list">' +
            '<div class="chzn-search" id="' + id + '-search" style="display:none"><input type="text" name="key" ></div>' +
            '<hr /><ul id="' + id + '-ul" class="chzn-results">';

        for (var i = 0; i < opts.length; i++) {
            html += '<li class=" active-result group-option' + id + '-list ">';
            if (opts[i].selected) {
                html += '<input type="checkbox" id="' + id + '-item-' + i + '" value="' + opts[i].text + '" checked="true">';
            } else {
                html += '<input type="checkbox" id="' + id + '-item-' + i + '" value="' + opts[i].text + '">';
            }
            html += '<span style="padding-left: 10px;" for="' + id + '-item-' + i + '">' + opts[i].text + '</span></li>';
        }
        html += '</ul><hr/>';
        html += '<div class="mopt" id="' + id + '-opt" style="maegin-top:-12px;margin-bottom: 8px;margin-left:8px;"> ' +
            '<input id="' + id + '-ok" class="btn btn-mini btn-inverse" value="Confirm" style="width:50px;"><span style="margin-left: 6px;">' +
            '<input id="' + id + '-cancel" class="btn btn-mini" value="Cancel"  style="width:50px;"/>'
        html += '</div></div>';
        return html;
    }

    var DropDownList = function (select_id, selected_ids, container_id, onupdate) {
        this.onupdate = onupdate;
        this.select_id = select_id;
        this.selected_ids = document.getElementById(selected_ids).value;
        this.container_id = container_id;

        var sync = false;
        this.select;
        this.tmp_options_selected = [];
        this.getSelect = function () {
            if (this.select == undefined) {
                this.select = q('#' + this.select_id);
            }
            return this.select;
        }

        function onclick(dropdownlist) {
            return function () {
                var index = get_index(this.id);
                if (sync) {
                    dropdownlist.getSelect().options[index].selected = this.checked;
                } else {
                    dropdownlist.tmp_options_selected[index] = this.checked;
                }
            }
        }

        this.update = function () {
            if (!sync && this.tmp_options_selected) {
                var opts = this.getSelect().options;
                for (var i = 0; i < opts.length; i++) {
                    opts[i].selected = this.tmp_options_selected[i];
                }
            }
            this.onupdate.call(this);
        }


        this.show = function () {
            q('#' + this.container_id).innerHTML = html(this.select_id, this.getSelect().options);

            if (!sync) {
                var opts = this.getSelect().options;
                for (var i = 0; i < opts.length; i++) {
                    this.tmp_options_selected[i] = opts[i].selected;
                }
            }

            q('#' + this.select_id + '-ok').onclick = function (list) {
                return function () {
                    hide(q('#' + list.select_id + '-list'));
                    list.update();
                }
            }(this);
            q('#' + this.select_id + '-cancel').onclick = function (list) {
                return function () {
                    hide(q('#' + list.select_id + '-list'));
                }
            }(this);
            var opts = this.getSelect().options;
            for (var i = 0; i < opts.length; i++) {
                q('#' + this.select_id + '-item-' + i).onclick = onclick(this);
            }
            var has_options_selected = initSelectedItem(this.select_id, this.selected_ids, this.getSelect().options);
            for (var i = 0; i < opts.length; i++) {
                if (has_options_selected[i]) {
                    this.tmp_options_selected[i] = "true";
                }
            }
            this.update();
        }

        this.text = function () {
            var texts = [];
            var opts = this.getSelect().options;
            for (var i = 0; i < opts.length; i++) {
                if (opts[i].selected) {
                    texts[texts.length] = opts[i].text;
                }
            }
            return texts.join(',');
        };
        return this;
    }

    function initSelectedItem(selectId, selectedIds, opts) {
        var has_options_selected = [];
        if (selectedIds.indexOf(",") >= 0) {
            var selectedIds = selectedIds.split(",");
            for (var id_index in selectedIds) {
                for (var i = 0; i < opts.length; i++) {
                    if (selectedIds[id_index] == opts[i].value) {
                        var selectedItemId = selectId + '-item-' + i;
                        document.getElementById(selectedItemId).checked = true;
                        has_options_selected[i] = "true";
                    }
                }

            }
        } else {
            for (var i = 0; i < opts.length; i++) {
                var selectedItemId = selectId + '-item-' + i;
                if (selectedIds == opts[i].value) {
                    document.getElementById(selectedItemId).checked = true;
                    has_options_selected[i] = "true";
                }
            }
        }
        return has_options_selected;
    }

    function createfun(params) {
        return new DropDownList(params.select_id, params.selected_ids, params.list_container_id, params.onupdate);
    }

    window['mdropdownlist'] = createfun;

})
    ();
