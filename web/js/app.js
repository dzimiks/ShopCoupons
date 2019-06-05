$(document).ready(() => {
    const restTable = document.getElementById('rest-table').tBodies[0];
    const form = document.getElementById('add-coupon-form');

    const result = {
        addRow: (data) => {
            let tr = document.createElement('tr');
            let id = data.id;
            let info = [data.product, data.shopName, data.originalPrice, data.discountedPrice, `${Math.round(data.sale * 100) / 100}%`];
            let cnt = 0;

            for (let i of info) {
                let td = document.createElement('td');
                td.appendChild(document.createTextNode(i));

                if (cnt === 2) {
                    td.classList.add('red-text');
                } else if (cnt === 3) {
                    td.classList.add('green-text');
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
                    success: item => {
                        if (item === 'true') {
                            restTable.removeChild(tr);
                        }
                    }
                });
            });

            let tdbt = document.createElement('td');
            tdbt.appendChild(btnDelete);
            tr.appendChild(tdbt);
        },

        addCoupon: () => {
            $.post('rest/coupons', $('#add-coupon-form').serialize(), data => {
                result.addRow(data);
                $('#add-coupon-form')[0].reset();
            });
        },

        submitForm: (e) => {
            if (e.preventDefault) {
                e.preventDefault();
            }

            result.addCoupon();
            return false;
        },

        fillForm: () => {
            $.get('rest/coupons', data => {
                for (let d of data) {
                    result.addRow(d);
                }
            });
        }
    };

    $.get('rest/shops', data => {
        for (let d of data) {
            $('#shop-list').append(new Option(d.name, d.id));
        }
    });

    result.fillForm();
    form.addEventListener('submit', result.submitForm);
});