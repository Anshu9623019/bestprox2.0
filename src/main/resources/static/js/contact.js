console.log("contact.js");
const baseUrl = "http://localhost:8081"

const viewContactModal = document.getElementById("view_contact_modal");

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

const instanceOptions = {
    id: 'view_contact_modal',
    override: true
  };

  const contactModal = new Modal(viewContactModal,options,instanceOptions);

  function openContactModal(){
    contactModal.show()
  }


  async function loadContactdata(id){
    console.log("id")
    console.log(id)
    const url = `${baseUrl}/api/contacts/${id}`;
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const contact = await response.json();
    console.log(contact);
    document.getElementById("contact_name").innerHTML = contact.name
    document.getElementById("contact_email").innerHTML = contact.email
    document.getElementById("contact_image").src = contact.picture;
    document.getElementById("contact_description").innerHTML = contact.desription;
    document.getElementById("contact_address").innerHTML = contact.address;
    document.getElementById("contact_address").innerHTML = contact.address;
    document.getElementById("contact_address").innerHTML = contact.address;

    if(contact.fevorite){
      document.getElementById("contact_address").innerHTML = contact.favorite;
    }else{
      document.getElementById("contact_address").innerHTML = "Not Fevorite Contact"
    }

    openContactModal()
  } catch (error) {
    console.error(error.message);
  }
  }

  //deleteContact

function deleteContact(id) {
    Swal.fire({
        title: "Do you want to delete the contact?",
        icon : "warning",
        cancelButtonColor : "red",
        confirmButtonColor : "blue",
        showCancelButton: true,
        confirmButtonText: "Delete",
      }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
         const url = `${baseUrl}/user/contacts/delete/`+id;
         window.location.replace(url);
          Swal.fire("Delete!", "", "success");
        } else if (result.isDenied) {
          Swal.fire("Changes are not delete", "", "info");
        }
      });
  }