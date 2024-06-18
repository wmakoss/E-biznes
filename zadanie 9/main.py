
from openai import OpenAI

client = OpenAI(api_key='YOUR_API_KEY')

def questionToChatGPT(message):
    chat_completion = client.chat.completions.create(messages=[{"role": "user","content": message,}], model="gpt-3.5-turbo",)
    return chat_completion.choices[0].message.content

while True:
    message = input("User: ")
    if message == "":
        break
    result = questionToChatGPT(message)
    print("Bot: ", end="")
    print(result)
