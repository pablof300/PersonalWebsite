class Message < ApplicationRecord

  EMAIL_REGEX = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i

  validates :message, presence: true, length: { minimum: 1, maximum: 1000 }
  validates :sender, presence: true, length: { minimum: 3, maximum: 30 }

  validates :email, presence: true,
  format: { with: EMAIL_REGEX, message: "Your email is invalid" },
  length: { minimum: 3, maximum: 50}
end
