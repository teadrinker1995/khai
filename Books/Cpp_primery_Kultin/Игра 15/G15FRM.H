//---------------------------------------------------------------------------

#ifndef g15frmH
#define g15frmH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <Menus.hpp>
//---------------------------------------------------------------------------
class TForm1 : public TForm
{
__published:	// IDE-managed Components

    void __fastcall FormPaint(TObject *Sender);
    void __fastcall FormShow(TObject *Sender);

    void __fastcall NewGame();  // ����� ����
    void __fastcall Mixer();    // ���������� �����
    void __fastcall ShowPole(); // ���������� ������� ����

    void __fastcall FormMouseDown(TObject *Sender, TMouseButton Button,
          TShiftState Shift, int X, int Y);

    
private:
     Graphics::TBitmap *pic; // ��������
public:
    __fastcall TForm1(TComponent* Owner);
};

extern PACKAGE TForm1 *Form1;
#endif
