/*
    MP3 ����� � ����������� ���������.
    ������������� ������ � ����������� MediaPlayer.
    �������� ��� ������ Play/Stop ����������� �� �������.

*/

#include <vcl.h>
#pragma hdrstop

#include "mp3main.h"

#pragma package(smart_init)
#pragma resource "*.dfm"
TForm1 *Form1;

// ����������� �����
__fastcall TForm1::TForm1(TComponent* Owner)
    : TForm(Owner)
{
    /* ������� ��������� MediaPlayer.
       ���������� MediaPlayer1 ��������� � ����������
       ����� (��. mp3main.h), ��� �� ��������� ������
       �� MPlayer.hpp (��������� #include "MPlayer.hpp" ).
    */
    MediaPlayer1 = new TMediaPlayer(Form1->Handle);
}


#include "FileCtrl.hpp"  // ��� ������� � TSearchRec

AnsiString SoundPath; // ���� � MP3 ������
int min,sec;   // ����� ��������������� (������, �������)


int mode = 0;  // 0 - ����� "����"
               // 1 - ����� "���������������"

/* ��������� vaweOutSetVolume � �������� ���������
   ���������� ������� �����, ������� ���� ��������
  ���������� ��������� ������ ������, ������� - �������.
  ��������� ����� ������� ��� TVolume, ������� ��������c��
  ����������� ����������� ��������� ������� ������.
  ������������ ��������� ������ ������������� �������� 0xFFFF.
*/
union TVolume{
          unsigned long Volume;
          struct
		    {
			    Word Left;
			    Word Right;
		    };
    } volume;

// ������ ������ ���������
void __fastcall TForm1::FormCreate(TObject *Sender)
{
    PlayList("");

    // ���������� ������ ���������� ��������� � ������������
    // � ������� �������, ������������� � �������
    waveOutGetVolume(0,&volume.Volume);
    TrackBar1->Position = - volume.Left;

    ListBox1->Color = (TColor)RGB(56,176,222);
}

// ��������� ������ MP3 ������, ����������� � ��������� ��������
void __fastcall TForm1::PlayList(AnsiString path)
{
    TSearchRec SearchRec;  // ��������� SearchRec �������� ����������
                           // � �����, ��������������� ������� ������

    ListBox1->Clear();
    // ������������ ������ mp3 ������
    if ( FindFirst(path + "*.mp3", faAnyFile, SearchRec) != 0 )
    {
        // � ��������� �������� ��� mp3-������
        SpeedButton2->Enabled = false;
        SpeedButton3->Glyph->LoadFromResourceID((int)HInstance,103);
        SpeedButton4->Enabled = false;
        Label1->Caption = "";
        return;
    }

    // � �������� ���� ���� � ����������� mp3
    // ������� ��� ����� ����� � ������
    ListBox1->Items->Add(SearchRec.Name);
    // ���� � �������� ���� ������ ����� � ����������� WAV
    while (FindNext(SearchRec) == 0)
              ListBox1->Items->Add(SearchRec.Name);

    ListBox1->ItemIndex  =  0;
    Label1->Caption = ListBox1->Items->Strings [ListBox1->ItemIndex];
    SpeedButton2->Enabled = false;
    if (ListBox1->Count == 1)
        SpeedButton4->Enabled = false;
    else
        SpeedButton4->Enabled = true;
     SpeedButton3->Glyph->LoadFromResourceID((int)HInstance,101);    
}

// �������������� ��������������� MP3 �����, ��� ��������
// �������� � ������ ListBox
void __fastcall TForm1::Play()
{
    Label1->Caption = ListBox1->Items->Strings [ListBox1->ItemIndex];
    MediaPlayer1->FileName = SoundPath +
                               ListBox1->Items->Strings [ListBox1->ItemIndex];

    MediaPlayer1->Open();
    MediaPlayer1->Play();
    min = 0;
    sec = 0;
    Timer1->Enabled = true;
}

// ���������� ���������������
void __fastcall TForm1::Stop()
{
    MediaPlayer1->Stop();
    Timer1->Enabled = false;
    Label2->Caption = "0";
    Label4->Caption = "00";
}

// ������ �� ������ Play/Stop
// ( �������� ��� ������ ����� ��������� �� ������� )
void __fastcall TForm1::SpeedButton3Click(TObject *Sender)
{
    if ( mode == 1 )
    {
        //SpeedButton3->Glyph->LoadFromFile("play_blue16.bmp");
        SpeedButton3->Glyph->LoadFromResourceID((int)HInstance, 101);
        SpeedButton3->Hint = "���������������";
        Stop();
        mode = 0;
    }
    else {
        //SpeedButton3->Glyph->LoadFromFile("stop_blue16.bmp");
        SpeedButton3->Glyph->LoadFromResourceID((int)HInstance, 102);
        SpeedButton3->Hint = "����";
        Play();
        mode = 1;
    }
}

// ������ �� �������
void __fastcall TForm1::Timer1Timer(TObject *Sender)
{
    // ��������� ������� ���������������
    if ( sec < 59 )
    {
        sec++;
        if ( sec < 10)
            Label4->Caption = "0" + IntToStr(sec);
        else
            Label4->Caption = IntToStr(sec);
    }
    else
    {
        sec = 0;
        min++;
        Label2->Caption = IntToStr(min);
        Label4->Caption = "00";
    }

    // ��������� ��������������� �������� �����?
    if ( MediaPlayer1->Position < MediaPlayer1->Length )
        // ��������������� ������� ���������� �� ���������
        return;

    // ��������������� ������� ���������� ���������
    Stop();

    if ( ListBox1->ItemIndex < ListBox1->Count - 1 )
    {
         ListBox1->ItemIndex += 1;
         Play(); // �������������� ��������������� ���������� MP3 �����
         if ( ListBox1->ItemIndex == ListBox1->Count - 1 )
             SpeedButton4->Enabled = false;
    }
    else {
        // ��������� ��������������� ���������� MP3 �����
        //SpeedButton3->Glyph->LoadFromFile("play_blue16.bmp");
        SpeedButton3->Glyph->LoadFromResourceID((int)HInstance, 101);
        SpeedButton3->Hint = "���������������";
        mode = 0;
    }
}

// ������ �� ������ "� ���������� �����"
void __fastcall TForm1::SpeedButton4Click(TObject *Sender)
{
    if ( mode == 1 )
        Stop();  // ���������� ��������������� ������� ����������

    ListBox1->ItemIndex += 1;
    Label1->Caption = ListBox1->Items->Strings [ListBox1->ItemIndex];

    // ��������� �, ���� ����, �������� ���������
    // ������ �������� � ���������� � ����������� �����
    if ( ListBox1->ItemIndex == ListBox1->Count - 1)
        SpeedButton4->Enabled = false;

    if ( ! SpeedButton2->Enabled  )
        SpeedButton2->Enabled = true;

    if ( mode == 1)
        Play(); // �������������� ��������������� ��������� ����������
}

// ������ �� ������ "� ����������� �����"
void __fastcall TForm1::SpeedButton2Click(TObject *Sender)
{
     if ( mode == 1 )
        Stop();  // ���������� ��������������� ������� ����������

     ListBox1->ItemIndex -= 1;
     Label1->Caption = ListBox1->Items->Strings [ListBox1->ItemIndex];

     // ��������� �, ���� ����, �������� ���������
     // ������ �������� � ���������� � ����������� �����
     if ( ! SpeedButton4->Enabled )
        SpeedButton4->Enabled = true;

     if ( ListBox1->ItemIndex == 0 )
        SpeedButton2->Enabled = false;


     if (mode == 1 )
        Play(); // �������������� ��������������� ���������� ����������
}

#include "FileCtrl.hpp"

// ������ �� ������ Eject - ����� ��������
void __fastcall TForm1::SpeedButton1Click(TObject *Sender)
{
    AnsiString dir;
    if ( SelectDirectory("�������� �������","",dir) )
    {
        if ( mode == 1) // ����� "���������������"
        {
            Stop();
            //SpeedButton3->Glyph->LoadFromFile("play_blue16.bmp");
            SpeedButton3->Glyph->LoadFromResourceID((int)HInstance, 101);
            SpeedButton3->Hint = "���������������";
            Stop();
            mode = 0;
        }
        SoundPath = dir + "\\";
        PlayList(SoundPath);
    }
}

// ������ �� ����� ����� (����������)
void __fastcall TForm1::ListBox1Click(TObject *Sender)
{
    if ( MediaPlayer1->Mode == 2) // ����� � ������ ���������������
    {
        Stop(); // ���������� ��������������� ������� ����������
        Play(); // �������������� ��������������� ��������� ����������
    }

    // ��������, ���� ����, ��������� ������
    // �������� � ���������� � ��������� ����������
    if (ListBox1->ItemIndex == 0 )
        SpeedButton2->Enabled = false;
    else
        SpeedButton2->Enabled = true;

    if (ListBox1->ItemIndex == ListBox1->Count -1  )
        SpeedButton4->Enabled = false;
    else
        SpeedButton4->Enabled = true;
}

#include "mmsystem.hpp"
// ������������ ������� ��������� ���������� ���������
void __fastcall TForm1::TrackBar1Change(TObject *Sender)
{
    volume.Left = - TrackBar1->Position;
    volume.Right = - TrackBar1->Position;
    waveOutSetVolume(0,volume.Volume);
}

