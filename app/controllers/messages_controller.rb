class MessagesController < ApplicationController
  def create
    message = Message.new(message_params)
    if message.save
    end
  end

private

  def message_params
    params.require(:message).permit(:sender, :email, :message)
  end

end
