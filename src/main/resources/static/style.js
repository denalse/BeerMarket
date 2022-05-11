
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

      
    const image_input = document.querySelector("#image_input");
    
    var uploaded_image = "";

    image_input.addEventListener("change", function() {
        const reader = new FileReader();
        reader.addEventListener("load", () => {
          uploaded_image = reader.result;
          document.querySelector("#display_image").style.backgroundImage = `url(${uploaded_image})`;
        // console.log(image_input.value);
        });
        reader.readAsDataURL(this.files[0]);
      })

    
    



    