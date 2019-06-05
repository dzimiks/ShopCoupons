$(document).ready(function () {
    const restTable = document.getElementById('rest-table').tBodies[0];
    const form = document.getElementById('add-coupon-form');

    const result = {
        addCoupon: () => {
            $.post('rest/coupons', $('#add-coupon-form').serialize(), function (data) {
                this.addRow(data);
                $('#add-coupon-form')[0].reset();
            });
        },

        processForm: e => {
            if (e.preventDefault) {
                e.preventDefault();
            }

            this.addCoupon();
            return false;
        },

        fillForm: () => {
            $.get('rest/coupons', function (data) {
                for (let d of data) {
                    this.addRow(d);
                }
            });
        },

        addRow: data => {
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
    };

    $.get('rest/shops', data => {
        for (let d of data) {
            $('#shop-list').append(new Option(d.name, d.id));
        }
    });

    result.fillForm();

    if (form.attachEvent) {
        form.attachEvent('submit', result.processForm());
    } else {
        form.addEventListener('submit', result.processForm());
    }
});