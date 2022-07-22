class App {

    static DOMAIN = location.origin;
    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dwzktyh7a/image/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_150,h_100,q_100";
    static BASE_URL_ORDER_ITEM = "http://localhost:8080/api/orders";
    static BASE_URL_PRODUCT = "http://localhost:8080/api/products/";

    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                position: 'middle',
                icon: 'error',
                title: t,
                showConfirmButton: false,
                timer: 2500
            })
        }

        static ShowConfirmSuspend(t) {
            return Swal.fire({
                position: 'middle',
                icon: 'warning',
                title: t,
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                reverseButtons: true
            });
        }
    }

    static IziToast = class {
        static showSuccessAlert(t) {
            iziToast.show({
                title: 'Success',
                message: t,
                position: 'topRight',
                color: 'green',
                timeout: 2500,
            });
        }

        static showErrorAlert(t) {
            iziToast.show({
                title: 'Error',
                message: t,
                position: 'topRight',
                color: 'red',
                timeout: 2500,
            });
        }
    }

}

class Product {
    constructor(id, nameProduct, price, category, categoryName,
                description, fileId, fileName, fileFolder) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.category = category;
        this.categoryName = categoryName;
        this.description = description;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
    }
}

class Category {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

class Order {
    constructor(id, totalBill, createAt) {
        this.id = id;
        this.totalBill = totalBill;
        this.createAt = createAt
    }
}

class OrderItem {
    constructor(id, idProduct, nameProduct, categoryName, idOrder, price, quantity, total) {
        this.id = id;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.categoryName = categoryName;
        this.idOrder = idOrder;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

}