$(function () {
    $('#jstree_demo_div').jstree();
});
$('#jstree_demo_div').on("changed.jstree", function (e, data) {
    var sel = data.node.text;
    switch (sel) {
    case "Пермь":
        mymap.setView([58.0099, 56.25], 13);
        break;
    case "Березники":
        mymap.setView([59.41, 56.78], 13);
        break;
    case "Чусовой":
        mymap.setView([58.27, 57.83], 13);
        break;
	case "test1":
        mymap.setView([58.012724,56.181788], 16);
        break;
	case "test2":
        mymap.setView([58.003221,56.206545], 16);
        break;
	case "test3":
        mymap.setView([58.007661,56.186351], 16);
        break;	
    }
});
