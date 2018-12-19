class SessionsController < ApplicationController
  def new
  end

  def create
    admin = Admin.find_by(username: params[:session][:username])
    if admin && admin.authenticate(params[:session][:password])
      flash[:success] = 'You have been logged in!'
      session[:admin_id] = admin.id
      redirect_to admin_path
    else
      flash.now[:error] = 'Your login information is incorrect'
      render :new
    end
  end

  def destroy
    session[:admin_id] = nil
    flash[:success] = 'You have logged out'
    redirect_to root_path
  end

end
