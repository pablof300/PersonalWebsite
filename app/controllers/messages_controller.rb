class MessagesController < ApplicationController
  def create
    message = Message.new(message_params)
    if message.save
      flash[:success] = 'Thank you for contacting me, I will be in touch shortly.'
      redirect_to root_path
    else
      flash.now[:error] = 'Some of your information in your message is incorrect'
      render 'pages/index'
    end
  end

private

  def message_params
    params.require(:message).permit(:sender, :email, :message)
  end

end
