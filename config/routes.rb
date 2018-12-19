Rails.application.routes.draw do
  post 'message', to:'messages#create'

  get 'login', to: 'sessions#new'
  post 'login', to: 'sessions#create'
  delete 'logout', to: 'sessions#destroy'

  get 'admin', to: 'admins#panel'

  root 'pages#index'
end
