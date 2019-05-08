Rails.application.routes.draw do
  post 'message', to:'messages#create'

  get 'login', to: 'sessions#new'
  post 'login', to: 'sessions#create'
  delete 'logout', to: 'sessions#destroy'

  get 'admin', to: 'admins#panel'

  get "/earthskipper", to: redirect('https://earthskipper.herokuapp.com')

  root 'pages#index'

  comfy_route :cms_admin, path: "/blogadmin"
  # Ensure that this route is defined last
  comfy_route :cms, path: "/blog"
end
