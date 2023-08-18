from db.session import get_db
from fastapi import APIRouter
from fastapi import Depends
from sqlalchemy.orm import Session
from langchain.chat_models import ChatOpenAI
from langchain.schema import HumanMessage, SystemMessage

router = APIRouter()


@router.get("/query={query}")
def create_user(query: str, db: Session = Depends(get_db)):
    chat = ChatOpenAI(openai_api_key="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"
                      , model="gpt-3.5-turbo-0613")
    messages = [HumanMessage(content= query)]
    ans = chat(messages).content

    return ans
