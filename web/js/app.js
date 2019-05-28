let restTable = document.getElementById('rest-table').tBodies[0];

$(document).ready(function () {
    $.get('rest/coupons/shops', function (data) {
        for (let d of data) {
            $('#shop-list').append(new Option(d.name, d.id));
        }
    });

    fill();

    let form = document.getElementById('add-coupon-form');

    if (form.attachEvent) {
        form.attachEvent('submit', processForm);
    } else {
        form.addEventListener('submit', processForm);
    }

    function createCoupon() {
        $.post('rest/coupons', $('#add-coupon-form').serialize(), function (data) {
            console.log(data);
            addEntry(data);
            $('#add-coupon-form')[0].reset();
        });
    }

    function processForm(e) {
        if (e.preventDefault) {
            e.preventDefault();
        }

        createCoupon();
        return false;
    }

    function fill() {
        $.get('rest/coupons', function (data) {
            for (let d of data) {
                addEntry(d);
            }
        });
    }

    function addEntry(data) {
        let tr = document.createElement('tr');
        let id = data.id;
        let info = [data.product, data.shopName, data.originalPrice, data.discountedPrice, `${Math.round(data.sale)}%`];
        let cnt = 0;

        for (let i of info) {
            let td = document.createElement('td');
            td.appendChild(document.createTextNode(i));

            if (cnt === 2) {
                td.classList.add("red-text");
            } else if (cnt === 3) {
                td.classList.add("green-text");
            }

            tr.appendChild(td);
            cnt++;
        }

        restTable.appendChild(tr);

        let btnDelete = document.createElement('button');

        btnDelete.setAttribute('type', 'button');
        btnDelete.classList.add('btn', 'btn-danger');
        btnDelete.innerText = 'Delete';
        btnDelete.addEventListener('click', e => {
            $.ajax({
                type: 'DELETE',
                url: 'rest/coupons/' + id,
                success: function (item) {
                    if (item === 'true') {
                        restTable.removeChild(tr);
                    }
                }
            });
        });

        let tdbt = document.createElement('td');
        tdbt.appendChild(btnDelete);
        tr.appendChild(tdbt);
    }
});