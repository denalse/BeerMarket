// function for upload and display image
    const imageInput = document.getElementById("imageInput");
    const previewContainer = document.getElementById("displayImage");
    const previewImage = previewContainer.querySelector(".image-upload");

    imageInput.addEventListener("change", function() {
        const file = this.files[0];

        if (file) {
            const reader = new FileReader();
            
            reader.addEventListener("load", function() {
                console.log(this);
                previewImage.setAttribute("src", this.result);
            });

            reader.readAsDataURL(file);
        } else {
            previewImage.setAttribute("src", "");
        }
    });
    
// function for eye icon on password
    function verifyPassword() {
      var x = document.getElementById("id_password");
      if (x.type === "password") {
          x.type = "text";
      } else {
          x.type = "password";
      }
      }

      function verifyPassword2() {
      var x = document.getElementById("id_password2");
      if (x.type === "password") {
          x.type = "text";
      } else {
          x.type = "password";
      }
      }


